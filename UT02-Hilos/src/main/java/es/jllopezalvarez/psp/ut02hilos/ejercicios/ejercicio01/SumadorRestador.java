package es.jllopezalvarez.psp.ut02hilos.ejercicios.ejercicio01;

public class SumadorRestador extends Thread {
    private static final long NUM_LIMIT = 2_000_000_000;
    private final boolean tengoQuesumar;


    public SumadorRestador(boolean tengoQuesumar) {
        this.tengoQuesumar = tengoQuesumar;
    }

    @Override
    public void run() {


        long acumulador = 0;

        for (long num = 1; num <= NUM_LIMIT; num++) {
            if (tengoQuesumar) {
                acumulador += num;
            } else {
                acumulador -= num;
            }
        }

        System.out.printf("La %s de los primeros %d números es %d.\n",
                tengoQuesumar ? "suma" : "resta", NUM_LIMIT, acumulador);
    }
}
