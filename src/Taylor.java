import java.util.Scanner;

public class Taylor {

    public static double factorial(int n) {
        double resultado = 1;
        for (int i = 1; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    public static double taylor(double x, double a, int ter) {
        double resultado = 0;


        for (int n = 0; n < ter; n++) {
            double term = Math.pow((x - a), n) / factorial(n) * derivada(a, n);
            resultado += term;
        }

        return resultado;
    }


    public static double derivada(double a, int n) {
        return Math.exp(a);
    }

    public static void metodoTaylor(Scanner t) {
        System.out.println("Evaluaremos la función f(x) = e^x utilizando la serie de Taylor.");

        System.out.print("Ingrese el punto de evaluación x: ");
        double x = t.nextDouble();

        System.out.print("Ingrese el punto alrededor del cual se evaluará la serie de Taylor a: ");
        double a = t.nextDouble();


        System.out.print("Ingrese el número de términos en la serie de Taylor: ");
        int ter = t.nextInt();

        double resultado = taylor(x, a, ter);


        System.out.println("El valor aproximado de f(" + x + ") utilizando " + ter + " términos de la serie de Taylor es: " + resultado);

        t.close();
    }
}