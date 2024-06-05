import java.util.Scanner;

public class Exactitud {
    public static void metodoExactitud(Scanner scanner){
        int i = 0;
        double valR;
        double[] vals = new double[5];
        System.out.println("Ingrese el valor real");
        valR = scanner.nextDouble();
        System.out.println("Ingrese los valores");
        for (int j = 0; j < 5; j++) {
            vals[j] = scanner.nextDouble();
        }
        boolean check = false;
        for (int j = 0; j < 5; j++) {
            if ((vals[j] - valR) < 0.1 && (vals[i] - valR) > -0.1) {
                if (j == 4) {
                    check = true;
                }
            } else {
                break;
            }
        }
        if (check) {
            System.out.println("Es exacto");
        } else {
            System.out.println("No es exacto");
        }
        check = true;
        for (int j = 0; j < 5; j++) {
            for (int k = 1; k < 5; k++) {
                if ((vals[j] - vals[k]) > 0.1 || (vals[j] - vals[k]) < -0.1) {
                    check = false;
                    break;
                }
            }
            if (!check) {
                break;
            }
        }
        if (check) {
            System.out.println("Es preciso");
        } else {
            System.out.println("No es preciso");
        }
    }
}
