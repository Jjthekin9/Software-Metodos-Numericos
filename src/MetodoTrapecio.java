import java.util.Scanner;

public class MetodoTrapecio {
    public static void metodoTrapecio(Scanner scanner){
        // Solicita al usuario que elija la función a integrar.
        System.out.println("Elige una función para integrar:");
        System.out.println("1: x^2");
        System.out.println("2: cos(x)");
        System.out.println("3: e^x");
        System.out.print("Introduce tu elección (1-3): ");
        int choice = scanner.nextInt();

        // Solicita al usuario los límites de integración y el número de trapecios.
        System.out.print("Introduce el límite inferior a: ");
        double a = scanner.nextDouble();
        System.out.print("Introduce el límite superior b: ");
        double b = scanner.nextDouble();
        System.out.print("Introduce el número de trapecios n: ");
        int n = scanner.nextInt();

        // Calcula y muestra el resultado de la integral aproximada.
        double resultado = MetodoTrapecio.trapecio(choice, a, b, n);
        System.out.println("La integral aproximada con " + n + " trapecios es: " + resultado);

        // Calcula y muestra una aproximación del valor "exacto" usando un número muy alto de trapecios.
        int nExacto = 10000;
        double resultadoExacto = MetodoTrapecio.trapecio(choice, a, b, nExacto);
        System.out.println("La integral aproximada con " + nExacto + " trapecios (valor 'exacto') es: " + resultadoExacto);

        // Calcula y muestra el error absoluto entre la aproximación inicial y la aproximación "exacta".
        double errorAbsoluto = Math.abs(resultadoExacto - resultado);
        System.out.println("El error absoluto entre la aproximación y el valor 'exacto' es: " + errorAbsoluto);

        scanner.close();
    }
    public static double xCuadrado(double x) {
        return x * x;
    }

    // Método para calcular el coseno de x. Utiliza la función Math.cos de Java para obtener el coseno de x.
    public static double cosenoDeX(double x) {
        return Math.cos(x);
    }

    // Método para calcular e^x, es decir, la función exponencial de x. Utiliza Math.exp para obtener el resultado.
    public static double eALaX(double x) {
        return Math.exp(x);
    }

    // Método de Trapecio para calcular la integral aproximada de una función.
    // Recibe el identificador de la función (funcion), los límites de integración (a y b) y el número de trapecios (n).
    public static double trapecio(int funcion, double a, double b, int n) {
        double h = (b - a) / n; // Calcula el tamaño del paso h.
        double suma = 0.5 * (evaluarFuncion(funcion, a) + evaluarFuncion(funcion, b)); // Suma los valores de la función en los extremos a y b.

        // Bucle para sumar los valores de la función en los puntos intermedios.
        for (int i = 1; i < n; i++) {
            double x = a + i * h; // Calcula cada uno de los puntos intermedios.
            suma += evaluarFuncion(funcion, x); // Suma los valores de la función en los puntos intermedios.
        }

        return suma * h; // Multiplica la suma por el tamaño del paso h para obtener la integral aproximada.
    }

    // Método para evaluar la función matemática seleccionada por el usuario.
    public static double evaluarFuncion(int funcion, double x) {
        switch (funcion) {
            case 1: return xCuadrado(x);
            case 2: return cosenoDeX(x);
            case 3: return eALaX(x);
            default: throw new IllegalArgumentException("Número de función no válido");
        }
    }
}
