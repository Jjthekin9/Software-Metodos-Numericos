import java.util.ArrayList;
import java.util.Arrays;

public class Polinomio {
    ArrayList<TerminoX> terminos;
    int grado;

    public static class TerminoX implements Comparable<TerminoX>{
        double coeficiente;
        int potencia;
        
        TerminoX(double coeficiente, int potencia){
            this.coeficiente = coeficiente;
            this.potencia = potencia;
        }
        
        TerminoX(double coeficiente){
            this.coeficiente = coeficiente;
            potencia = 0;
        }

        @Override
        public int compareTo(TerminoX o) {
            return this.potencia - o.potencia;
        }
    }

    Polinomio(TerminoX x, TerminoX ... xi){
        terminos.add(x);
        terminos.addAll(Arrays.asList(xi));
        terminos.sort(null);
    }

    public Polinomio sumar(Polinomio p, Polinomio ... pi) {
        //TODO: Replace with proper return
        return new Polinomio(new TerminoX(0));
    }
}