
import java.math.BigDecimal;
import java.math.MathContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author X870
 */
public class Powers {

    int znak;
    int wykladnikCechy;
    String cecha = "";
    int[] mantysa = new int[23];
    String result;
    static float fl;
    int mantysaIndex = 0;
    String mantyString= "";
    BigDecimal reszta;

    public String rozbierzFloata(float fl) {
        this.fl = fl;
        if (fl > 0) {
            znak = 0;
        } else {
            znak = 1;
        }
        if(fl>1||fl<-1){
            for (int i = 1024; i >= 0; i--) {
                if (Math.pow(2, i) > Math.abs(fl)) {
                    wykladnikCechy = i - 1;
             }
         }
        }
        else {
            for (int i = -1024; i <= -1; i++) {
                if (Math.pow(2, i) > Math.abs(fl)) {
                    wykladnikCechy = i - 1;
                    System.out.println(wykladnikCechy);
                }
            }
        }
        cecha = Integer.toBinaryString(wykladnikCechy + 127);
        if(cecha.length()<8){
            for(int i = cecha.length(); i<8; i++){
                cecha = "0"+cecha;
            }
        }
        System.out.println("cecha :");
        ustalBitMantysy(wykladnikCechy, BigDecimal.valueOf(fl));
        for(int i = 0; i<23; i++){
            mantyString += mantysa[i];
            }
        result = znak + cecha + mantyString;
        return result;
    }

    public void ustalBitMantysy(int exponent, BigDecimal operand) {
        if (mantysaIndex<23) {
            System.out.println("exponent "+exponent);
            System.out.println("operand "+operand);
            if(mantysaIndex==0){
                reszta = operand.abs().subtract(new BigDecimal(2).pow(exponent, MathContext.DECIMAL128));
            }
            else if(mantysa[mantysaIndex-1]==1){
                reszta = operand.abs().subtract(new BigDecimal(2).pow(exponent, MathContext.DECIMAL128));
            }
            System.out.println(reszta);
            int compareTo = reszta.abs().compareTo(new BigDecimal(2).pow(exponent-1, MathContext.DECIMAL128));
            System.out.println(new BigDecimal(2).pow(exponent-1, MathContext.DECIMAL128));
            if (compareTo!=1) {
                mantysa[mantysaIndex++] = 0;
                System.out.println("reszta mniejsza");
                ustalBitMantysy(exponent-1, operand);
            } else {
                mantysa[mantysaIndex++] = 1;
                System.out.println("reszta wiÄ™ksza");
                ustalBitMantysy(exponent-1, reszta);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Powers().rozbierzFloata(-0.27f));
        
    }
    
    

}
