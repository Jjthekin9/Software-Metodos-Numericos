import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InterpolacionNewton {

    // Método para calcular las diferencias divididas
    public static double[][] calcularDiferenciasDivididas(List<Double> x, List<Double> y) {
        int n = y.size();
        double[][] dd = new double[n][n];

        // La primera columna es simplemente los valores de y
        for (int i = 0; i < n; i++) {
            dd[i][0] = y.get(i);
        }

        // Calcular las diferencias divididas
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                dd[i][j] = (dd[i + 1][j - 1] - dd[i][j - 1]) / (x.get(i + j) - x.get(i));
            }
        }

        return dd;
    }

    // Método para construir el polinomio interpolante
    public static String interpolacionNewton(List<Double> x, List<Double> y) {
        int n = y.size();
        StringBuilder polinomio = new StringBuilder();

        double[][] dd = calcularDiferenciasDivididas(x, y);

        // Construir el polinomio
        polinomio.append(dd[0][0]);
        for (int j = 1; j < n; j++) {
            polinomio.append(" + ");
            polinomio.append(dd[0][j]);
            for (int i = 0; i < j; i++) {
                polinomio.append(" * (x - ").append(x.get(i)).append(")");
            }
        }

        return polinomio.toString();
    }

    // Método para imprimir la tabla con la tabulación
    public static void imprimirTabla(List<Double> x, List<Double> y, double[][] dd) {
        int n = x.size();
        System.out.println("Tabla de Diferencias Divididas:");
        System.out.println("J\tX_j\tf(x)\tDiferencias Divididas");

        for (int i = 0; i < n; i++) {
            System.out.print(i + "\t" + x.get(i) + "\t" + y.get(i) + "\t");
            for (int j = 0; j < n - i; j++) {
                System.out.print(dd[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void metodoInterpolacionNewton(Scanner scanner) {
        // Pedir al usuario que ingrese la cantidad de puntos
        System.out.print("Ingrese la cantidad de puntos: ");
        int n = scanner.nextInt();

        // Inicializar listas para almacenar los puntos
        List<Double> x = new ArrayList<>();
        List<Double> y = new ArrayList<>();

        // Pedir al usuario que ingrese los puntos
        for (int i = 0; i < n; i++) {
            System.out.print("Ingrese el valor de x" + (i + 1) + ": ");
            x.add(scanner.nextDouble());
            System.out.print("Ingrese el valor de y" + (i + 1) + ": ");
            y.add(scanner.nextDouble());
        }

        // Calcular el polinomio interpolante
        String polinomio = interpolacionNewton(x, y);

        // Calcular las diferencias divididas
        double[][] dd = calcularDiferenciasDivididas(x, y);

        // Imprimir la tabla de tabulación
        imprimirTabla(x, y, dd);

        // Imprimir el polinomio interpolante
        System.out.println("Polinomio interpolante: P(x) = " + polinomio);

        scanner.close();
    }
}
