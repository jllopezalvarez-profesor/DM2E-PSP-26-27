package es.jllopezalvarez.psp.ut02hilos.ejercicios.ejercicio05;

public class Sumador extends Thread {
    private static final int CANTIDAD_SUMA = 1;
    private static final int VECES_SUMA = 100;

    private static int suma = 0;

    @Override
    public void run() {
        for (int i = 0; i < VECES_SUMA; i++) {
            int nuevaSuma = suma;
            nuevaSuma += CANTIDAD_SUMA;
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            suma = nuevaSuma;
        }
    }

    public static int getSuma() {
        return suma;
    }
}
