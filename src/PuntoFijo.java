import java.util.List;
import java.util.Scanner;

public class PuntoFijo {
    public static void metodoPuntoFijo(Scanner scanner){
        int opci2;
        System.out.println("Punto fijo ");
        System.out.println("Elija");
        System.out.println("1. Método del punto fijo con e^-x");
        System.out.println("2. Método del punto fijo con 0.4e^(x^2)");
        System.out.println("3. Salir");
        opci2 = scanner.nextInt();
        switch (opci2) {
            case 1:
                System.out.println("Punto fijo con e^-x:");
                PuntoFijo.metodoPuntoFijo(PuntoFijo::funcion1);
                break;
            case 2:
                System.out.println("Punto fijo con 0.4e^(x^2):");
                PuntoFijo.metodoPuntoFijo(PuntoFijo::funcion2);
                break;
            case 3:
                System.out.println("Gracias Vuelva pronto ");
                break;

            default:
                System.out.println("ingresa un numero correcto por favor ");
        }
    }
    static double funcion1(double x) {
        return Math.exp(-x);
    }


    // Definición de la función g(x) como 0.4e^(x^2)
    static double funcion2(double x) {
        return 0.4 * Math.exp(Math.pow(x, 2));
    }

    // Método del punto fijo
    static void metodoPuntoFijo(FuncionG funcionG) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingresa tu valor inicial (x):");
        double x = teclado.nextDouble();

        System.out.println("Ingresa tu número máximo de iteraciones:");
        int maxIteraciones = teclado.nextInt();

        System.out.println("Ingresa tu margen de error:");
        double error = teclado.nextDouble();

        int iteraciones = 0; // Variable para almacenar el número de iteraciones realizadas
        System.out.println("------------------------------------------------------------------------");
        System.out.printf("%-12s %-12s %-12s\n", "Interaccion ", "Valor ini ", "Error");

        for (int i = 1; i <= maxIteraciones; i++) {
            double e = x;
            x = funcionG.calcular(x); // Calcular el próximo valor de x usando la función g(x)
            e = Math.abs(x - e);
            System.out.printf("%-12d %-12.6f %-12.4f\n", i, x, e);

            if (e <= error) { // Comprobar si el error es menor o igual al error tolerado
                iteraciones = i; // Almacenar el número de iteraciones realizadas
                break; // Salir del bucle while si se alcanza el error tolerado
            }
        }
        System.out.println("---------------------------------------------------------------------------");

        if (iteraciones == 0) {
            System.out.println("No se encontró solución después de " + maxIteraciones + " iteraciones.");
        } else {
            System.out.println("Total de iteraciones: " + iteraciones);
        }
    }

    // Interfaz funcional para representar una función g(x)
    interface FuncionG {
        double calcular(double x);
    }
}
