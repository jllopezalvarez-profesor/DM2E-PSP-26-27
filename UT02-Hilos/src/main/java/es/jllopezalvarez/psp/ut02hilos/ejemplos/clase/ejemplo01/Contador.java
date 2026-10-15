package es.jllopezalvarez.psp.ut02hilos.ejemplos.clase.ejemplo01;

public class Contador extends Thread {

    @Override
    public void run()  {
        // super.run();

        for (int i=0; i<10; i++){
            System.out.printf("He contado hasta %d.\n", i );
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
