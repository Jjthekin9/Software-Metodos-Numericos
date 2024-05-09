import java.util.HashMap;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.parser.ParseException;

public class PruebaMetodos {
    public static void main(String[] args) throws Exception {
        Expression función = new Expression("e^x");
        double inicioIntervalo = 0;
        double finIntervalo = 1;
        int divisiones = 6;
        double integral = simpson(función, inicioIntervalo, finIntervalo, divisiones);
        System.out.println(integral);
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

    public static double simpson(Expression función, double inicioIntervalo, double finIntervalo, int divisiones) 
    throws ParseException, EvaluationException {
        if (finIntervalo <= inicioIntervalo) {
            throw new IllegalArgumentException("EL fin del intervalo debe ser mayor que el inicio!!");
        }
        función.validate();

        double incremento = (finIntervalo - inicioIntervalo)/divisiones;
        double límite = finIntervalo - incremento;
        double sumaEvaluaciones = función.with("x", inicioIntervalo).evaluate().getNumberValue().doubleValue();
        System.out.println("F(x0) = " + sumaEvaluaciones);
        sumaEvaluaciones += simpsonEvaluarTerminoNon(función, inicioIntervalo + incremento, incremento, límite, 0);
        System.out.println("Sum(F(x1), F(xn-1)) = " + sumaEvaluaciones);
        sumaEvaluaciones += función.with("x", finIntervalo).evaluate().getNumberValue().doubleValue();
        return (incremento/3)*sumaEvaluaciones;
    }

    public static double simpsonEvaluarTerminoNon(Expression función, double termino, double incremento, double límite, double sumaEvaluaciones) 
    throws EvaluationException, ParseException {
        if (termino > límite) {
            return sumaEvaluaciones;
        }
        sumaEvaluaciones += (4*(función.with("x", termino).evaluate().getNumberValue().doubleValue()));
        System.out.println("F(" + termino + ") = " + función.with("x", termino).evaluate().getNumberValue().doubleValue());
        System.out.println("sum = " + sumaEvaluaciones + "\n");
        return simpsonEvaluarTerminoPar(función, termino + incremento, incremento, límite, sumaEvaluaciones);
    }

    public static double simpsonEvaluarTerminoPar(Expression función, double termino, double incremento, double límite, double sumaEvaluaciones) 
    throws EvaluationException, ParseException {
        if (termino > límite) {
            return sumaEvaluaciones;
        }
        sumaEvaluaciones += (2*(función.with("x", termino).evaluate().getNumberValue().doubleValue()));
        System.out.println("F(" + termino + ") = " + función.with("x", termino).evaluate().getNumberValue().doubleValue());
        System.out.println("sum = " + sumaEvaluaciones + "\n");
        return simpsonEvaluarTerminoNon(función, termino + incremento, incremento, límite, sumaEvaluaciones);
    }
}