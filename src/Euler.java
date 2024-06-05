import java.util.Scanner;

public class Euler {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Entrada del usuario para la función derivada f(x, y) = 2 * x * y
        System.out.println("Introduce la función derivada f(x, y). Por ejemplo, '2 * x * y':");
        String funcionDerivada = scanner.nextLine();

        // Valores iniciales ingresados por el usuario
        System.out.println("Introduce el valor inicial de x (x0):");
        double x0 = scanner.nextDouble();

        System.out.println("Introduce el valor inicial de y (y0):");
        double y0 = scanner.nextDouble();

        System.out.println("Introduce el tamaño del paso (h):");
        double h = scanner.nextDouble();

        System.out.println("Introduce el número de pasos (n):");
        int n = scanner.nextInt();

        // Llamamos al método de Euler
        double[] resultado = euler(x0, y0, h, n);
        System.out.printf("Después de %d pasos, x = %.2f y y = %.2f%n", n, resultado[0], resultado[1]);
    }

    public static void metodoEuler(Scanner scanner) {
        // Entrada del usuario para la función derivada f(x, y) = 2 * x * y
        System.out.println("Introduce la función derivada f(x, y). Por ejemplo, '2 * x * y':");
        String funcionDerivada = scanner.nextLine();

        // Valores iniciales ingresados por el usuario
        System.out.println("Introduce el valor inicial de x (x0):");
        double x0 = scanner.nextDouble();

        System.out.println("Introduce el valor inicial de y (y0):");
        double y0 = scanner.nextDouble();

        System.out.println("Introduce el tamaño del paso (h):");
        double h = scanner.nextDouble();

        System.out.println("Introduce el número de pasos (n):");
        int n = scanner.nextInt();

        // Llamamos al método de Euler
        double[] resultado;
        double x = x0;
        double y = y0;
        for (int i = 0; i < n; i++) {
            y += h * (2 * x * y);
            x += h;
        }
        resultado = new double[]{x, y};

        System.out.printf("Después de %d pasos, x = %.2f y y = %.2f%n", n, resultado[0], resultado[1]);
    }

    // Método de Euler
    public static double[] euler(double x0, double y0, double h, int n) {
        double x = x0;
        double y = y0;
        for (int i = 0; i < n; i++) {
            y += h * calcularDerivada(x, y);
            x += h;
        }
        return new double[]{x, y};
    }

    // Método para calcular la derivada basado en la entrada del usuario
    public static double calcularDerivada(double x, double y) {
        // Aquí se debe implementar la lógica para interpretar y calcular la derivada basada en la entrada del usuario
        // Por ejemplo, si el usuario ingresa "2 * x * y", se debe calcular y devolver ese valor
        // Esta es una funcionalidad avanzada y requiere un análisis de la entrada del usuario o una interfaz gráfica
        return 2 * x * y; // Esto es solo un placeholder
    }
}