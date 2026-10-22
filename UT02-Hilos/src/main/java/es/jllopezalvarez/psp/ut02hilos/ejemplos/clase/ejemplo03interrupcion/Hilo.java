package es.jllopezalvarez.psp.ut02hilos.ejemplos.clase.ejemplo03interrupcion;

public class Hilo extends Thread {
    @Override
    public void run() {

        // Este hilo se ejecuta siempre, permanentemente.
        try {
            while (true) {
                System.out.println(System.currentTimeMillis());
                Thread.sleep(1000);
                descansarOtroRatito();

                if (Thread.interrupted()){
                    throw new InterruptedException();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Ha saltado InterruptedException. Se sale del bucle porque el try está rodeándolo.");

        }
    }

    private void descansarOtroRatito() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            
        }
    }


    // Se evalúa en la condición del while
    public void runOk01() {

        // Este hilo se ejecuta siempre, permanentemente.
        try {
            while (!Thread.interrupted()) {
                System.out.println(System.currentTimeMillis());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Ha saltado InterruptedException. Se sale del bucle porque el try está rodeándolo.");

        }
    }


    // Esto no para porque nunca mira el flag de haber sido interrumpido
    public void run01() {
        // Este hilo se ejecuta siempre, permanentemente.
        long millis = System.currentTimeMillis();
        while (true) {
            if ((System.currentTimeMillis() - millis) > 1000) {
                System.out.println(System.currentTimeMillis());
                millis = System.currentTimeMillis();
            }
        }
    }

    // Solo funciona porque no hay estados WAITING o TIMED_WAITING (p. ej. al llamar a sleep)
    public void run02() {
        // Este hilo se ejecuta siempre, permanentemente.
        long millis = System.currentTimeMillis();
        while (!Thread.interrupted()) {
            if ((System.currentTimeMillis() - millis) > 1000) {
                System.out.println(System.currentTimeMillis());
                millis = System.currentTimeMillis();
            }
        }
    }


    // Este tampoco se para bien porque se ignora Interrupted Exception
    public void run03() {

        // Este hilo se ejecuta siempre, permanentemente.
        long millis = System.currentTimeMillis();
        while (!Thread.interrupted()) {
            if ((System.currentTimeMillis() - millis) > 100) {
                System.out.println(System.currentTimeMillis());
                millis = System.currentTimeMillis();
            }

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                System.out.println("Ha saltado InterruptedException pero se ignora...");
            }
        }
    }

}
