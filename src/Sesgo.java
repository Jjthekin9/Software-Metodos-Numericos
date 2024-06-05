import java.util.Scanner;

public class Sesgo {

    // Método para calcular el sesgo de un conjunto de datos
    public static double calcularSesgo(double[] datos) {
        int n = datos.length;
        double suma = 0;
        double media = calcularMedia(datos);

        // Calcular la suma de las desviaciones al cubo
        for (double dato : datos) {
            suma += Math.pow(dato - media, 3);
        }

        // Calcular el sesgo
        double sesgo = suma / (n * Math.pow(calcularDesviacionEstandar(datos), 3));
        return sesgo;
    }

    // Método para calcular la media de un conjunto de datos
    public static double calcularMedia(double[] datos) {
        double suma = 0;
        for (double dato : datos) {
            suma += dato;
        }
        return suma / datos.length;
    }

    // Método para calcular la desviación estándar de un conjunto de datos
    public static double calcularDesviacionEstandar(double[] datos) {
        double media = calcularMedia(datos);
        double suma = 0;
        for (double dato : datos) {
            suma += Math.pow(dato - media, 2);
        }
        return Math.sqrt(suma / datos.length);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la cantidad de números:");
        int cantidadNumeros = scanner.nextInt();

        double[] datos = new double[cantidadNumeros];

        System.out.println("Ingrese los números uno por uno:");

        for (int i = 0; i < cantidadNumeros; i++) {
            datos[i] = scanner.nextDouble();
        }

        double sesgo = calcularSesgo(datos);
        System.out.println("El sesgo de los datos es: " + sesgo);

        scanner.close();
    }

    public static void metodoSesgo(Scanner scanner) {
        System.out.println("Ingrese la cantidad de números:");
        int cantidadNumeros = scanner.nextInt();

        double[] datos = new double[cantidadNumeros];

        System.out.println("Ingrese los números uno por uno:");

        for (int i = 0; i < cantidadNumeros; i++) {
            datos[i] = scanner.nextDouble();
        }

        int n = datos.length;
        double suma = 0;
        double media = calcularMedia(datos);

        // Calcular la suma de las desviaciones al cubo
        for (double dato : datos) {
            suma += Math.pow(dato - media, 3);
        }

        // Calcular el sesgo
        double sesgo = suma / (n * Math.pow(calcularDesviacionEstandar(datos), 3));
        System.out.println("El sesgo de los datos es: " + sesgo);
    }
}
