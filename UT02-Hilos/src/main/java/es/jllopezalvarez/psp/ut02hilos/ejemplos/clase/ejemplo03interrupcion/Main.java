package es.jllopezalvarez.psp.ut02hilos.ejemplos.clase.ejemplo03interrupcion;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Hilo hilo = new Hilo();
        hilo.start();

        // espero 5 segundos
        Thread.sleep(5000);

        // Interrumpir el hilo
        System.out.println("Voy a interrumpir al hilo");
        hilo.interrupt();
        System.out.println("Ya he interrumpido al hilo");

        // Espero a que termine
        hilo.join();

    }
}
