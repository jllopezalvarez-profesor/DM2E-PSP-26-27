package es.jllopezalvarez.psp.ut02hilos.ejercicios.ejercicio03;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Crear el sumador
        SumadorRestador sumador = new SumadorRestador(true, "Hilo sumador 01");

        // Crear el restador
        SumadorRestador restador = new SumadorRestador(false, "Hilo restador 01");

        long statrMillis = System.currentTimeMillis();

        // Arrancar los dos hilos
        sumador.start();
        restador.start();

        // Esperar a que terminen con espera activa
        while (sumador.isAlive() || restador.isAlive()){
            System.out.println("Sigo esperando a que terminen los hilos... duermo medio segundo");
            Thread.sleep(500);
        }

        // Mensaje de que han terminado
        System.out.printf("Han terminado los 2 hilos y han tardado %d milisegundos.\n", System.currentTimeMillis() - statrMillis);
    }
}
