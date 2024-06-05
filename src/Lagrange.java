import java.util.Scanner;

public class Lagrange {
    // Función para interpolar utilizando el método de Lagrange
    public static double interpolar(double[] x, double[] y, double xIn) {
        double yOut = 0; // Variable para almacenar el valor interpolado

        // Itera sobre todos los puntos de datos
        for (int i = 0; i < x.length; i++) {
            double t = y[i]; // Inicializa el término de Lagrange con el valor de y en el punto i

            // Calcula el producto de los términos de Lagrange
            for (int j = 0; j < x.length; j++) {
                if (j != i) { // Evita dividir por cero
                    t = t * (xIn - x[j]) / (x[i] - x[j]); // Calcula el término de Lagrange para el punto i
                }
            }
            yOut += t; // Suma el término de Lagrange al total
        }
        return yOut; // Devuelve el valor interpolado
    }

    // Método para leer los valores de x e y desde la entrada estándar
    public static void datoReal(double[] x, double[] y, Scanner scanner) {
        for (int i = 0; i < x.length; i++) {
            System.out.print("Ingrese el valor de x[" + i + "]: ");
            x[i] = scanner.nextDouble();

            System.out.print("Ingrese el valor de y[" + i + "]: ");
            y[i] = scanner.nextDouble();
        }
    }

    public static String obtenerPolinomio() {
        Polinomio.TerminoX x;
        return "";
    }

    public static void main(String[] args) {
        Scanner t= new Scanner(System.in);

        try {
            System.out.print("Ingrese el número de puntos de datos: ");
            int numDatoPuntos = t.nextInt();

            double[] x = new double[numDatoPuntos]; // Arreglo para almacenar los valores de x
            double[] y = new double[numDatoPuntos]; // Arreglo para almacenar los valores de y

            System.out.println("Ingrese los valores de x e y:");
            datoReal(x, y, t); // Método para leer los valores de x e y

            System.out.print("Ingrese el valor para interpolar: ");
            double n = t.nextDouble(); // Lee el valor para interpolar desde la entrada estándar

            // Calcula el valor interpolado utilizando el método de Lagrange
            double valorInterpolado = interpolar(x, y, n);
            // Muestra el valor interpolado en la salida estándar
            System.out.println("El valor interpolado en " + n + " es: " + valorInterpolado);
        } catch (Exception e) {
            System.out.println("Error: Ingrese un valor válido.");
        } finally {
            t.close(); // Cierra el scanner para evitar fugas de recursos
        }
    }
}
