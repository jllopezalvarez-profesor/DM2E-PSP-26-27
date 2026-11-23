package es.jllopezalvarez.psp.ut02hilos.ejercicios.ejercicio13;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int NUM_CAMAREROS = 10;
    private static final int NUM_COCINEROS = 3;

    private static final int TIEMPO_DESCANSO_CAMAREROS = 500;
    private static final int TIEMPO_PREPARACION_PLATOS = 10;

    private static final int TAMANIO_BARRA = 5;

    private static final int TIEMPO_PROGRAMA_MS = 10_000;

    public static void main(String[] args) throws InterruptedException {
        // Crear la barra
        Barra barra = new Barra(TAMANIO_BARRA);

        // Lista de cocinetos
        List<Cocinero> cocineros = new ArrayList<>();

        // Crear cocineros
        for (int i = 1; i <= NUM_COCINEROS; i++) {
            Cocinero cocinero = new Cocinero("Cocinero " + i, barra, TIEMPO_PREPARACION_PLATOS);
            cocinero.start();
            cocineros.add(cocinero);
        }

        // Lista para camareros
        List<Camarero> camareros = new ArrayList<>();
        for (int i = 1; i <= NUM_CAMAREROS; i++) {
            Camarero camarero = new Camarero("Camarero " + i, barra, TIEMPO_DESCANSO_CAMAREROS);
            camarero.start();
            camareros.add(camarero);
        }

        // TODO: Hacer esto con espera activa, y evaluando el tiempo transcurrido
        Thread.sleep(TIEMPO_PROGRAMA_MS);

        // Parar cocineros y camareros
        for (Cocinero cocinero : cocineros) {
            cocinero.interrupt();
            cocinero.join();
        }
        for (Camarero camarero : camareros) {
            camarero.interrupt();
            camarero.join();
        }

        // He terminado
        System.out.println("Programa terminado");






    }
}
