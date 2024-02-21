public class PruebaMetodos {
    public static void main(String[] args) throws Exception {
        double num = 123.44564789; //Número de entrada
        int decimales = 5; //Número de decimales
        System.out.println("Número: " + num +", Decimales: " + decimales);
        System.out.println("Truncado: " + truncar(num, decimales));
        System.out.println("Redondeado: " + redondear(num, decimales));
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
}