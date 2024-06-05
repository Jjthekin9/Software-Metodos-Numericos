import java.util.Scanner;
public class ErrorRelativo {
    public static void metodoErrorRelativo(Scanner sc) {
        System.out.println("Bienvenido al programa que calcula el error relativo");

        System.out.print("Ingrese el valor exacto: ");
        double valorExacto = sc.nextInt();

        System.out.print("Ingrese el valor aproximado: ");
        double valorAproximado = sc.nextDouble();

        double errorAbsoluto = errorAbs(valorExacto, valorAproximado);

        double resultado = errorRel(errorAbsoluto,valorExacto);
        System.out.print("El error relativo es: "+(resultado*100));
        System.out.println();
    }
    static double errorAbs(double valorExacto, double valorAproximado){
        return Math.abs(valorAproximado-valorExacto);
    }

    static double errorRel(double errorAbs, double valorExacto){
        return Math.abs(errorAbs/valorExacto);
    }
}
