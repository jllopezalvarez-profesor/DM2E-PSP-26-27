package es.jllopezalvarez.psp.ut02hilos.ejercicios.ejercicio03;

public class SumadorRestador extends Thread {
    private static final long NUM_LIMIT = 2_000_000_000;
    private final boolean tengoQuesumar;
    private final String nombreHilo;

    public SumadorRestador(boolean tengoQuesumar, String nombreHilo) {
        this.tengoQuesumar = tengoQuesumar;
        this.nombreHilo = nombreHilo;
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
//            try {
//                //Thread.sleep(1); // Hacemos más lento el hilo para que se pueda ver la espera activa en el hilo principal.
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }

        }

        System.out.printf("Soy '%s' y la %s de los primeros %d números es %d.\n",
                this.nombreHilo, tengoQuesumar ? "suma" : "resta", NUM_LIMIT, acumulador);
        System.out.printf("Soy '%s' y ya he terminado.\n", this.nombreHilo);
    }
}
