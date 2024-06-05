import java.util.Scanner;

public class PolinomioNewton {
    public static void metodoPolinomioNewton(Scanner scanner) {
        // Lectura de los puntos
        System.out.print("Ingrese el número de puntos: ");
        int n = scanner.nextInt();

        double[] x = new double[n];
        double[] y = new double[n];

        System.out.println("Ingrese los puntos (x y): ");
        for (int i = 0; i < n; i++) {
            System.out.print("x[" + i + "]: ");
            x[i] = scanner.nextDouble();
            System.out.print("y[" + i + "]: ");
            y[i] = scanner.nextDouble();
        }

        // Cálculo de la tabla de diferencias divididas
        double[][] dividedDifferences = new double[n][n];
        for (int i = 0; i < n; i++) {
            dividedDifferences[i][0] = y[i];
        }

        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                dividedDifferences[i][j] = (dividedDifferences[i + 1][j - 1] - dividedDifferences[i][j - 1]) / (x[i + j] - x[i]);
            }
        }

        // Mostrar la tabla de diferencias divididas
        System.out.println("Tabla de diferencias divididas:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                System.out.printf("%.4f\t", dividedDifferences[i][j]);
            }
            System.out.println();
        }

        // Lectura del número de puntos en los que se va a evaluar el polinomio
        System.out.print("Ingrese el número de puntos donde desea evaluar el polinomio: ");
        int m = scanner.nextInt();
        double[] evalPoints = new double[m];

        System.out.println("Ingrese los puntos x donde desea evaluar el polinomio: ");
        for (int i = 0; i < m; i++) {
            System.out.print("x_eval[" + i + "]: ");
            evalPoints[i] = scanner.nextDouble();
        }

        // Evaluación del polinomio interpolador en los puntos especificados
        for (int i = 0; i < m; i++) {
            double value = evalPoints[i];
            double result = evaluateNewtonPolynomial(x, dividedDifferences, n, value);
            System.out.printf("El valor interpolado en x = %.4f es: %.4f\n", value, result);
        }

        scanner.close();
    }

    // Método para evaluar el polinomio interpolador en un punto dado
    private static double evaluateNewtonPolynomial(double[] x, double[][] dividedDifferences, int n, double value) {
        double result = dividedDifferences[0][0];
        double product;

        for (int i = 1; i < n; i++) {
            product = dividedDifferences[0][i];
            for (int j = 0; j < i; j++) {
                product *= (value - x[j]);
            }
            result += product;
        }

        return result;
    }
}
