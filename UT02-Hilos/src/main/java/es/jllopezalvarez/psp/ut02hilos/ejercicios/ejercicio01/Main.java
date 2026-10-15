package es.jllopezalvarez.psp.ut02hilos.ejercicios.ejercicio01;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Crear el sumador
        SumadorRestador sumador = new SumadorRestador(true);

        // Crear el restador
        SumadorRestador restador = new SumadorRestador(false);

        long statrMillis = System.currentTimeMillis();

        // Arrancar los dos hilos
        sumador.start();
        restador.start();

        // Esperar a que terminen
        sumador.join();
        restador.join();

        // Mensaje de que han terminado
        System.out.printf("Han terminado los 2 hilos y han tardado %d milisegundos.\n", System.currentTimeMillis() - statrMillis);
    }
}
