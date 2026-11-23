package es.jllopezalvarez.psp.ut02hilos.ejemplos.adicionales.ejemplo02demonios;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Hilo hilo = new Hilo("Hilo de usuario");
        hilo.setDaemon(true);
        hilo.start();

        Thread.sleep(2000);

        System.out.println("El programa termina");

    }
}
