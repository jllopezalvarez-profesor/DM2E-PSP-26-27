package es.jllopezalvarez.psp.ut02hilos.ejercicios.ejercicio11;

import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {
    private static final int NUM_CARRILES = 4;
    private static final int NUM_COCHES = 20;
    private static final Faker faker = new Faker(Locale.forLanguageTag("es"));

    public static void main(String[] args) throws InterruptedException {
        // Crear puente
        Puente puente = new Puente(NUM_CARRILES);

        // Lista de coches
        List<Coche> coches = new ArrayList<>();

        for (int i = 0; i < NUM_COCHES; i++) {
            String matriculaEs = faker.regexify("[0-9]{4}[BCDFGHJKLMNPRSTVWXYZ]{3}");
            Coche coche = new Coche(matriculaEs, puente);
            coche.start();
            coches.add(coche);
        }

        while (coches.stream().anyMatch(Thread::isAlive)){
            Thread.sleep(1000);
            int libres = puente.getCarrilesLibres();
            int ocupados = NUM_CARRILES - libres;
            System.out.printf("[MAIN] El puente tiene %d carriles libres y %d ocupados.%n", libres, ocupados );
        }

        System.out.println("[Main] Programa finalizado");

    }

}
