import java.util.Scanner;

public class MenuMatematico {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        while (!salir) {
            System.out.println("Menú de Métodos Matemáticos:");
            System.out.println("1. Cifra significativa");
            System.out.println("2. Precisión");
            System.out.println("3. Exactitud");
            System.out.println("4. Sesgo");
            System.out.println("5. Error absoluto");
            System.out.println("6. Error relativo");
            System.out.println("7. Truncamiento");
            System.out.println("8. Redondeo");
            System.out.println("9. Método de bisección");
            System.out.println("10. Método de punto fijo");
            System.out.println("11. Interpolación de Newton");
            System.out.println("12. Método de Jacobi");
            System.out.println("13. Método de Gauss-Seidel");
            System.out.println("14. Diferenciación numérica");
            System.out.println("15. Regla del trapecio");
            System.out.println("16. Regla de Simpson");
            System.out.println("17. Polinomio de interpolación de Newton");
            System.out.println("18. Polinomio de interpolación de Lagrange");
            System.out.println("19. Regresión Lineal");
            System.out.println("20. Mínimos cuadrados");
            System.out.println("21. Método de Euler");
            System.out.println("22. Método de Taylor");
            System.out.println("23. Método de Adams-Bashforth");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Cifra significativa seleccionado.");
                    CifraSignificativa.metodoCifraSignificativa(scanner);
                    break;

                case 2:
                    System.out.println("Precisión seleccionado.");
                    Precision.metodoPresicion(scanner);
                    break;

                case 3:
                    System.out.println("Exactitud seleccionado.");
                    Exactitud.metodoExactitud(scanner);
                    break;

                case 4:
                    System.out.println("Sesgo seleccionado.");
                    Sesgo.metodoSesgo(scanner);
                    break;

                case 5:
                    System.out.println("Error absoluto seleccionado.");
                    break;

                case 6:
                    System.out.println("Error relativo seleccionado.");
                    ErrorRelativo.metodoErrorRelativo(scanner);
                    break;

                case 7:
                    System.out.println("Error absoluto Truncamiento seleccionado.");
                    Truncador.truncarValores(scanner);
                    break;

                case 8:
                    System.out.println("Error absoluto Redondeo seleccionado.");
                    Redondeador.redondearValores(scanner);
                    break;

                case 9:
                    System.out.println("Método de bisección seleccionado.");
                    Simpson.calcularSimpson(scanner);
                    break;

                case 10:
                    System.out.println("Método de punto fijo seleccionado.");
                    PuntoFijo.metodoPuntoFijo(scanner);
                    break;

                case 11:
                    System.out.println("Interpolación de Newton seleccionado.");
                    InterpolacionNewton.metodoInterpolacionNewton(scanner);
                    break;

                case 12:
                    System.out.println("Método de Jacobi seleccionado.");
                    Jacobi.calcularJacobi(scanner);
                    break;

                case 13:
                    System.out.println("Método de Gauss-Seidel seleccionado.");
                    GaussSeidel.metodoGaussSeidel(scanner);
                    break;

                case 14:
                    System.out.println("Diferenciación numérica seleccionado.");
                    break;

                case 15:
                    System.out.println("Regla del trapecio seleccionado.");
                    MetodoTrapecio.metodoTrapecio(scanner);
                    break;

                case 16:
                    System.out.println("Regla de Simpson seleccionado.");
                    Simpson.calcularSimpson(scanner);
                    break;

                case 17:
                    System.out.println("Polinomio de interpolación de Newton seleccionado.");
                    PolinomioNewton.metodoPolinomioNewton(scanner);
                    break;

                case 18:
                    System.out.println("Polinomio de interpolación de Lagrange seleccionado.");
                    Lagrange.calcularLagrange(scanner);
                    break;

                case 19:
                    System.out.println("Regresión Lineal seleccionado.");
                    new RegresionLineal();
                    break;

                case 20:
                    System.out.println("Mínimos cuadrados seleccionado.");
                    MinimosCuadrados.metodoMinimosCuadrados(scanner);
                    break;

                case 21:
                    System.out.println("Método de Euler seleccionado.");
                    Euler.metodoEuler(scanner);
                    break;

                case 22:
                    System.out.println("Método de Taylor seleccionado.");
                    Taylor.metodoTaylor(scanner);
                    break;

                case 23:
                    System.out.println("Método de Adams-Bashforth seleccionado.");
                    AdamsBashforth.metodoAdamsBashforth(scanner);
                    break;

                case 0:
                    salir = true;
                    System.out.println("Saliendo del menú");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, elige una opción del 0 al 23.");
            }
        }
        scanner.close();
    }
}
