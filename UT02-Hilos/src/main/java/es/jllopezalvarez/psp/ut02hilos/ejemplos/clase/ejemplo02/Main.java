package es.jllopezalvarez.psp.ut02hilos.ejemplos.clase.ejemplo02;

public class Main {



    public static void main(String[] args) throws InterruptedException {
        System.out.println("Iniciando el hilo principal");

        System.out.println("Creando tres hilos contadores...");
        Contador contador1 = new Contador();
        Contador contador2 = new Contador();
        Contador contador3 = new Contador();

        System.out.println("Arrancando los hilos contadores...");
        // contador1.run(); // No se arrancan hilos con RUN, sino con START
        contador1.start();
        contador2.start();
        contador3.start();
//        contador1.run();
//        contador2.run();
//        contador3.run();

        Thread.sleep(2000);

        System.out.println("Soy el hilo principal. He terminado mi descanso y voy a esperar a que acaben los contadores...");

        contador1.join();
        contador2.join();
        contador3.join();


        System.out.println("Fin del hilo principal");
    }


}
