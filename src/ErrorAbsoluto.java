import java.util.Scanner;
public class ErrorAbsoluto {
    public static void main(String[] args) {
        //Declaramos las variables a usar, para los datos númericos usamos variables double
        double vverda, vaprox, errAbs;
        int decimales;
        int opc;
        Scanner t = new Scanner(System.in);
        do {
            //Menú principal del programa
            System.out.println("=====Bienvenido al programa para sacar el valor absoluto=====");
            System.out.println("Selecciona una opcción del menú");
            System.out.println("1.Truncar número para sacar valor absoluto"); //Opción de truncado
            System.out.println("2.Redondear número para sacar valor absoluto"); //Opción de redondeado
            System.out.println("3.Salir");
            opc = t.nextInt();

            if (opc == 1) {
                //Se pide al usuario que ingrese el valor verdadero y el aproximado
                System.out.println("Dame el valor verdadero: ");
                vverda = t.nextDouble();
                System.out.println("Dame el valor aproximado: ");
                vaprox = t.nextDouble();
                //Así como el número de posiciones decimales deseadas
                System.out.println("Dame el numero de decimales a los que desea truncar");
                decimales = t.nextInt();

                //Se calcula el error absoluto restando el valor apróximado del verdadero
                errAbs = Math.abs(vverda - vaprox);
                //Imprimimos el valor completo, el número de decimales deseados y el valor truncado
                System.out.println("Error Absoluto: " + errAbs + ", Decimales: " + decimales);
                System.out.println("Truncado: " + truncar(errAbs, decimales));

            } else if (opc == 2) {
                System.out.println("Dame el valor verdadero: ");
                vverda = t.nextDouble();
                System.out.println("Dame el valor aproximado: ");
                vaprox = t.nextDouble();
                System.out.println("Dame el numero de decimales a los que desea redondear");
                decimales = t.nextInt();

                errAbs = Math.abs(vverda - vaprox);
                //Imprimimos el valor completo, el número de decimales deseados y el valor redondeado
                System.out.println("Error Absoluto: " + errAbs + ", Decimales: " + decimales);
                System.out.println("Redondeado: " + redondear(errAbs, decimales));
            } else if (opc == 3) {
                System.exit(0);
            }
        } while (opc <= 3);
        t.close();
    }

    public static double truncar(double numero, int decimales) {
        // Multiplicamos el número por 10 elevado al número de decimales deseados
        // para pasar ese número de decimales a la parte "entera"
        // 123.4567 * 10^2 = 12345.67
        // ^^ ^^
        numero = numero * Math.pow(10, decimales);
        // Usamos el m. floor para reducir los decimales restantes a 0
        numero = Math.floor(numero);
        // Dividimos el número por el mismo poder de 10 para regresar los
        // decimales a su lugar
        numero = numero / Math.pow(10, decimales);
        return numero;
    }

    public static double redondear(double numero, int decimales) {
        numero = numero * Math.pow(10, decimales);
        // Lo mismo que con el m. truncar pero usamos el m.rint para redondear
        // la parte entera hacía arriba si el siguiente decimal es >= 5
        numero = Math.rint(numero);
        numero = numero / Math.pow(10, decimales);
        return numero;
    }
}
