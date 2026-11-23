package es.jllopezalvarez.psp.ut02hilos.ejercicios.ejercicio13;

public class Camarero extends Thread {
    private final String nombre;
    private final Barra barra;
    private final long tiempoDescansoMs;

    public Camarero(String nombre, Barra barra, long tiempoDescansoMs) {
        this.nombre = nombre;
        this.barra = barra;
        this.tiempoDescansoMs = tiempoDescansoMs;
    }

    @Override
    public void run() {
        try {
            while (!this.isInterrupted()){
                System.out.printf("Soy el camarero %s y voy a coger algo de la barra.%n", nombre);
                String plato = barra.cogerPlato(nombre);
                Thread.sleep(tiempoDescansoMs);
            }
            System.out.printf("El camarero %s se ha interrumpido, sin lanzamiento de excepción.%n", nombre);
        } catch (InterruptedException e) {
            System.out.printf("El camarero %s se ha interrumpido, con excepción.%n", nombre);
        }


    }
}
