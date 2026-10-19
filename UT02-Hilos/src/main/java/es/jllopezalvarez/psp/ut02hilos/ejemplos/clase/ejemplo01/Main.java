package es.jllopezalvarez.psp.ut02hilos.ejemplos.clase.ejemplo01;

public class Main {



    public static void main(String[] args) throws InterruptedException {
        System.out.println("Iniciando el hilo principal");

        System.out.println("Creando un hilo contador...");
        Contador contador = new Contador();

        System.out.println("Arrancando el hilo contador");
        // contador.run(); // No se arrancan hilos con RUN, sino con START
        contador.start();

        System.out.println("Ya he arrancado el hilo contador, me duermo un poco.");

        Thread.sleep(2000);

        System.out.println("Fin del hilo principal");
    }


}
