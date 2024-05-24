import java.util.HashMap;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.parser.ParseException;

public class PruebaMetodos {
    public static void main(String[] args) throws Exception {
        Expression función = new Expression("(x-1)(x-2)");
        String resultado = función.evaluate().getStringValue();
        System.out.println(resultado);
    }

    //M. TRUNCAR
    public static double truncar(double numero, int decimales) {
        //Multiplicamos el número por 10 elevado al número de decimales deseados
        //para pasar ese número de decimales a la parte "entera"
        //123.4567 * 10^2 = 12345.67
        //    ^^               ^^
        numero = numero * Math.pow(10, decimales);
        //Usamos el m. floor para reducir los decimales restantes a 0
        numero = Math.floor(numero);
        //Dividimos el número por el mismo poder de 10 para regresar los
        //decimales a su lugar
        numero = numero / Math.pow(10, decimales);
        return numero;
    }

    //M. REDONDEAR
    public static double redondear(double numero, int decimales) {
        numero = numero * Math.pow(10, decimales);
        //Lo mismo que con el m. truncar pero usamos el m.rint para redondear
        //la parte entera hacía arriba si el siguiente decimal es >= 5
        numero = Math.rint(numero);
        numero = numero / Math.pow(10, decimales);
        return numero;
    }

    public static double[] jacobi(double[][] matrizCoeficientes, double[] resultados, double[] valoresIniciales, double tolerancia, int limiteIteraciones) {
        if (limiteIteraciones == 0) {
            return valoresIniciales;
        }

        double[] valoresSiguientes = new double[valoresIniciales.length];
        limiteIteraciones -= 1;

        double sum;
        for (int i = 0; i < valoresIniciales.length; i++) {
            sum = 0;
            for (int j = 0; j < matrizCoeficientes.length; j++) {
                if (j != i) {                    
                    sum += matrizCoeficientes[i][j] * valoresIniciales[j];
                }
            }
            valoresSiguientes[i] = (resultados[i] - sum)/matrizCoeficientes[i][i];
            System.out.println(i + " " + valoresSiguientes[i]);
            /*System.out.println("x" + i + ": " + valoresVariables[i]);
            System.out.println("b" + i + ": " + resultados[i]);
            System.out.println("sum: " + sum);
            System.out.println("aii: " + matrizCoeficientes[i][i]);*/
        }
        System.out.println();

        sum = 0;
        for (int i = 0; i < valoresIniciales.length; i++) {
            sum += Math.pow(valoresSiguientes[i] -  valoresIniciales[i], 2);
        }
        double toleranciaActual = Math.sqrt(sum);
        if (toleranciaActual <= tolerancia) {
            return valoresSiguientes;
        } else {
            return jacobi(matrizCoeficientes, resultados, valoresSiguientes, tolerancia, limiteIteraciones);
        }
    }

    public static void ranking(double[][] m) {
        int posiciónMayor;
        double mayor;
        for (double[] ds : m) {
            posiciónMayor = -1;
            mayor = Double.NEGATIVE_INFINITY;
            for (int i = 0; i < m.length; i++) {
                if (ds[i] > mayor) {
                    mayor = ds[i];
                    posiciónMayor = i;
                }
            }
            System.out.println(ds + "Mayor: " + mayor + ", Posción: " + posiciónMayor);
        }
        double sumaAbsCoeficientes;
        int posiciónDominante;
        double dominante;
        for (double[] ds : m) {
            sumaAbsCoeficientes = 0;
            posiciónDominante = -1;
            dominante = Double.NaN;
            for (int i = 0; i < ds.length; i++) {
                sumaAbsCoeficientes += Math.abs(ds[i]);
            }
            for (int i = 0; i < ds.length; i++) {
                if (sumaAbsCoeficientes < 2 * Math.abs(ds[i])) {
                    posiciónDominante = i;
                    dominante = ds[i];
                }
            }
            System.out.println(ds + "Dominante: " + dominante + ", Posción: " + posiciónDominante);
        }
    }

    public static double[][] hacerDominante(double[][] matriz) {
        HashMap<Integer, double[]> rankingEcuaciones = new HashMap<>();
        for (double[] coeficientes : matriz) {
            double sumaAbsCoeficientes = 0;
            for (int i = 0; i < coeficientes.length; i++) {
                sumaAbsCoeficientes += Math.abs(coeficientes[i]);
            }
            boolean diagonalDominante = false;
            for (int i = 0; i < coeficientes.length; i++) {
                if (sumaAbsCoeficientes < 2 * Math.abs(coeficientes[i])) {
                    diagonalDominante = true;
                    if (rankingEcuaciones.putIfAbsent(i, coeficientes) != null) {
                        diagonalDominante = false;
                        //System.out.println("Condición de error, ranking repetido");
                        // Condición de error, ranking repetido
                    }
                }
            }
            if (!diagonalDominante) {
                throw new IllegalArgumentException("La matriz no se puede convertir en diagonal estrictamente dominante");
                //System.out.println("Condición de error, no existe dominante");
                // Condición de error, no existe dominante
            }
        }
        for (int i = 0; i < matriz.length; i++) {
            matriz[i] = rankingEcuaciones.get(i);
            for (int j = 0; j < matriz.length; j++) {
                System.out.print(matriz[i][j] + " ");  
            }
            System.out.println();
        }
        return matriz;
    }

    //M. SIMPSON (regla de tercios)
    /*Método de integración numérica por polinomios de simpson
     *Recibe la funcíon a integrar (F(x)) como objeto de tipo "Expression" (de la librería EvalEx), el inicio (a) y fin (b) del
     *intervalo en el que se va a integrar y el número de divisiones (n) del intervalo.
     *El método puede arrojar una de dos excepciones si la función es inválida o surge un error al evaluarla.*/
    public static double simpson(Expression función, double inicioIntervalo, double finIntervalo, int divisiones) 
    throws ParseException, EvaluationException {
        if (finIntervalo <= inicioIntervalo) { //Validación del intervalo
            throw new IllegalArgumentException("EL fin del intervalo debe ser mayor que el inicio!!");
        }
        función.validate(); //Validación de la función

        double incremento = (finIntervalo - inicioIntervalo)/divisiones; //Calculo del incremento por division (b-a)/n
        double límite = finIntervalo - incremento; //Límite para las llamadas recursivas

        //Se inicia el acumulador de evaluaciones (f(x) en cada incremento) con el inicio del intervalo
        double sumaEvaluaciones = función.with("x", inicioIntervalo).evaluate().getNumberValue().doubleValue();
        //System.out.println("F(x0) = " + sumaEvaluaciones); 

        /*Se llama a los métodos recursivos para acumular las evaluaciones entre el inicio y fin del intervalo.
         *Los métodos recursivos alternan entre terminos pares y nones para multiplicarlos por el coeficiente que corresponda. */
        sumaEvaluaciones += simpsonEvaluarTerminoNon(función, inicioIntervalo + incremento, incremento, límite, 0);
        //System.out.println("Sum(F(x1), F(xn-1)) = " + sumaEvaluaciones);

        //Por último se agrega al acumulador la evaluación del fin del intervalo
        sumaEvaluaciones += función.with("x", finIntervalo).evaluate().getNumberValue().doubleValue();

        //Retorna la aproximacíon de la integral definida multiplicando la suma de evaluaciones por el incremento/3 (regla de tercios)
        return (incremento/3)*sumaEvaluaciones;
    }

    //M. EVALUAR TÉRMINO NON
    /*Método recursivo complementario para el método de simpson, esté método recibe toda la información relevante para evaluar un
     *termino dentro del intervalo de la integral y acumularlo en la suma de evaluaciones.
     *Como indica la regla de tercios del método de Simpson, los terminos nones (considerando el inicio del intervalo como termino 0
     *se evaluan y se multiplican por 4.*/
    public static double simpsonEvaluarTerminoNon(Expression función, double término, double incremento, double límite, double sumaEvaluaciones) 
    throws EvaluationException, ParseException {
        if (término >= límite) { //Caso base, retorna la suma acumulada si el termino recibido es igual o mayor al límite
            return sumaEvaluaciones;
        }
        sumaEvaluaciones += (4*(función.with("x", término).evaluate().getNumberValue().doubleValue())); //Se acumula la evaluación
        System.out.println("F(" + término + ") = " + función.with("x", término).evaluate().getNumberValue().doubleValue());
        System.out.println("sum = " + sumaEvaluaciones + "\n");
        return simpsonEvaluarTerminoPar(función, término + incremento, incremento, límite, sumaEvaluaciones); //Llamada recursiva
    }

    //M. EVALUAR TÉRMINO PAR
    /*El otro método recursivo complementario para el método de Simpson, idéntico al método para evaluar términos nones con la única
     *diferencia de que los terminos pares se multiplican por 2 y no por 4.*/
    public static double simpsonEvaluarTerminoPar(Expression función, double término, double incremento, double límite, double sumaEvaluaciones) 
    throws EvaluationException, ParseException {
        if (término >= límite) {
            return sumaEvaluaciones;
        }
        sumaEvaluaciones += (2*(función.with("x", término).evaluate().getNumberValue().doubleValue()));
        System.out.println("F(" + término + ") = " + función.with("x", término).evaluate().getNumberValue().doubleValue());
        System.out.println("sum = " + sumaEvaluaciones + "\n");
        return simpsonEvaluarTerminoNon(función, término + incremento, incremento, límite, sumaEvaluaciones);
    }
}