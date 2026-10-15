package es.jllopezalvarez.psp.ut02hilos.ejemplos.clase.ejemplo02;

import java.util.Random;

public class Contador extends Thread {
    private static Random random = new Random();

    @Override
    public void run()  {
        // super.run();

        for (int i=0; i<10; i++){
            System.out.printf("Soy el hilo %s. ", this.threadId());
            System.out.printf("He contado hasta %d.\n", i );
            try {
                Thread.sleep(random.nextLong(0, 1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
