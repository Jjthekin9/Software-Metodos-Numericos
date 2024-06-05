import java.util.Scanner;

public class CifraSignificativa {
    public static void metodoCifraSignificativa(Scanner scanner){
        System.out.print("Ingrese un número: ");
        String numeroStr = scanner.nextLine();
        //verifica que si el numero tiene punto decimal
        if (numeroStr.contains(".")) {
            String str = String.valueOf(numeroStr);
            int intNumber = Integer.parseInt(str.substring(0, str.indexOf('.')));
            float decNumbert = Float.parseFloat(str.substring(str.indexOf('.')));
            int decNumberInt = Integer.parseInt(str.substring(str.indexOf('.') + 1));
            if(intNumber==0){
                int numerosSignificativos = contarNumerosSignificativosP(decNumberInt);
                System.out.println("El número de cifras significativas en ." + decNumberInt + " es: " + numerosSignificativos);
            }else{
                int numerosSignificativos = contarNumerosSignificativosP(intNumber)+(contarNumerosSignificativosS(decNumberInt));
                System.out.println("El número de cifras significativas en " + intNumber +decNumberInt+ " es: " + numerosSignificativos);
            }
        }else{
            int cantidadDigitos = 0;
            int numero = Integer. parseInt(numeroStr);
            int numerosSignificativos = contarNumerosSignificativosE(numero);
            System.out.println("El número de cifras significativas en " + numero + " es: " + numerosSignificativos);
        };
        int numero=0;
    }

    public static int contarNumerosSignificativosE(int numero) {
        String numeroStr=String.valueOf(numero);
        numeroStr = numeroStr.replaceAll("0+$", "");
        numeroStr = numeroStr.replaceAll("^0+", "");
        int cantidadDigitos = 0;
        for (char c : numeroStr.toCharArray()) {
            if (Character.isDigit(c)) {
                cantidadDigitos++;
            }
        }
        return cantidadDigitos;
    }
    public static int contarNumerosSignificativosP(int numero) {
        String numeroStr=String.valueOf(numero);
        numeroStr = numeroStr.replaceAll("^0+", "");
        int cantidadDigitos = 0;
        for (char c : numeroStr.toCharArray()) {
            if (Character.isDigit(c)) {
                cantidadDigitos++;
            }
        }
        return cantidadDigitos;
    }
    public static int contarNumerosSignificativosS(int numero) {
        String numeroStr=String.valueOf(numero);
        int cantidadDigitos = 0;
        for (char c : numeroStr.toCharArray()) {
            if (Character.isDigit(c)) {
                cantidadDigitos++;
            }
        }
        return cantidadDigitos;
    }
}
