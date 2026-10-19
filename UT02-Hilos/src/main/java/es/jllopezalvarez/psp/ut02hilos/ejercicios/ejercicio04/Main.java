package es.jllopezalvarez.psp.ut02hilos.ejercicios.ejercicio04;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // crear los hilos
        Hilo runnable1 = new Hilo("Hilo 1", 20, 50);
        Hilo runnable2 = new Hilo("Hilo 2", 20, 100);

        // Lanzar los hilos. Antes tengo que crear Threads
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        // Configuro el hilo 2 como demonio
        thread2.setDaemon(true);

        // Lanzar los hilos
        thread1.start();
        thread2.start();

        // Espero por el primer hilo
        thread1.join();

        // Indicar que terminamos
        System.out.println("El programa principal termina");




    }
}
