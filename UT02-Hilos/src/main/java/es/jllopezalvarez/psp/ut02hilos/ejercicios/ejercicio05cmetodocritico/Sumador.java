package es.jllopezalvarez.psp.ut02hilos.ejercicios.ejercicio05cmetodocritico;

public class Sumador extends Thread {
    private static final int CANTIDAD_SUMA = 1;
    private static final int VECES_SUMA = 100;

    private static int suma = 0;

    @Override
    public void run() {
        for (int i = 0; i < VECES_SUMA; i++) {
            actualizarSuma();
        }
    }

    private synchronized static void actualizarSuma() {
        int nuevaSuma = suma;
        nuevaSuma += CANTIDAD_SUMA;
        suma = nuevaSuma;
    }

//    private static void actualizarSuma2() {
//        synchronized (Sumador.class) {
//            int nuevaSuma = suma;
//            nuevaSuma += CANTIDAD_SUMA;
//            suma = nuevaSuma;
//        }
//    }

    public static int getSuma() {
        return suma;
    }
}
