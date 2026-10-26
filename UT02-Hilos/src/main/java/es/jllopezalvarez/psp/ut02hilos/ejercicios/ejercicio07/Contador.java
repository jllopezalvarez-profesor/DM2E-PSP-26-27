package es.jllopezalvarez.psp.ut02hilos.ejercicios.ejercicio07;

public class Contador implements Runnable {
    private final String nombre;
    private final long tiempoSiesta;

    public Contador(String nombre, long tiempoSiesta) {
        this.nombre = nombre;
        this.tiempoSiesta = tiempoSiesta;
    }

    @Override
    public void run() {

        try {
            long iteracion = 1;
            while (!Thread.interrupted()) {
                System.out.printf("Soy '%s' y voy por el número %d.\n", this.nombre, iteracion);
                Thread.sleep(tiempoSiesta);
                iteracion++;
            }
            System.out.printf("Soy '%s' y termino porque he detectado el fin con Thread.interrupted().\n", this.nombre);
        } catch (InterruptedException e) {
            System.out.printf("Soy '%s' y termino por excepción InterruptedException.\n", this.nombre);
        }
    }
}
