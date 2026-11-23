package es.jllopezalvarez.psp.ut02hilos.ejercicios.ejercicio13;

import net.datafaker.Faker;

public class Cocinero extends Thread {
    private final String nombre;
    private final Barra barra;
    private final long tiempoPreparacionMs;

    private static final Faker faker = new Faker();

    public Cocinero(String nombre, Barra barra, long tiempoPreparacionMs) {
        this.nombre = nombre;
        this.barra = barra;
        this.tiempoPreparacionMs = tiempoPreparacionMs;
    }


    @Override
    public void run() {
        try {
            while (!this.isInterrupted()){
                String plato = faker.food().dish();
                System.out.printf("Soy el cocinero %s y voy a poner en la barra %s.%n", nombre, plato);
                barra.ponerPlato(plato, nombre);
                Thread.sleep(tiempoPreparacionMs);
            }
            System.out.printf("El cocinero %s se ha interrumpido, sin lanzamiento de excepción.%n", nombre);
        } catch (InterruptedException e) {
            System.out.printf("El cocinero %s se ha interrumpido, con excepción.%n", nombre);
        }


    }
}
