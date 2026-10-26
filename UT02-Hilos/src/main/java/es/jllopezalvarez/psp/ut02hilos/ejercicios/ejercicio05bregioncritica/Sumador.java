package es.jllopezalvarez.psp.ut02hilos.ejercicios.ejercicio05bregioncritica;

public class Sumador extends Thread {
    private static final int CANTIDAD_SUMA = 1;
    private static final int VECES_SUMA = 100;

    private static int suma = 0;

    private static final Object lock = new Object();

    @Override
    public void run() {
        for (int i = 0; i < VECES_SUMA; i++) {
            synchronized (lock) {
                int nuevaSuma = suma;
                nuevaSuma += CANTIDAD_SUMA;
                suma = nuevaSuma;
            }
        }
    }

    public static int getSuma() {
        return suma;
    }
}
