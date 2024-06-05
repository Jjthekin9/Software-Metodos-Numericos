import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Precision {
    public static void metodoPresicion(Scanner scanner){
        System.out.println("Ingrese un número real:");
        double numeroReal = scanner.nextDouble();

        System.out.println("¿Cuántos números desea ingresar?");
        int cantidadNumeros = scanner.nextInt();

        List<Double> listaNumeros = new ArrayList<>();

        System.out.println("Ingrese los números:");

        for (int i = 0; i < cantidadNumeros; i++) {
            listaNumeros.add(scanner.nextDouble());
        }

        List<Double> numerosPrecisos = new ArrayList<>();

        for (double num : listaNumeros) {
            if (num != Math.floor(num)) {
                numerosPrecisos.add(num);
            }
        }

        double numeroAproximado = Precision.encontrarAproximado(numeroReal, numerosPrecisos);

        System.out.println("El número real ingresado es: " + numeroReal);
        System.out.println("La lista de números precisos es: " + numerosPrecisos);
        System.out.println("El número exacto es: " + numeroAproximado);
    }
    public static double encontrarAproximado(double numeroReal, List<Double> numerosPrecisos) {
        double numeroAproximado = numerosPrecisos.get(0); // Se inicializa con el primer número de la lista
        double diferenciaMinima = Math.abs(numeroReal - numeroAproximado);

        for (double num : numerosPrecisos) {
            double diferencia = Math.abs(numeroReal - num);
            if (diferencia < diferenciaMinima) {
                diferenciaMinima = diferencia;
                numeroAproximado = num;
            }
        }

        return numeroAproximado;
    }
}
