package es.jllopezalvarez.psp.ut02hilos.ejemplos.clase.ejemplo04sinchronized;

import net.datafaker.Faker;

import java.util.List;
import java.util.Locale;

public class GeneradorNombres extends Thread {

    private final Faker faker = new Faker(Locale.forLanguageTag("ES-es"));

    private final List<String> nombres;

    public GeneradorNombres(List<String> nombres) {
        this.nombres = nombres;
    }


    @Override
    public void run() {

        try {
            while(!Thread.interrupted()){
                synchronized (nombres) {
                   nombres.add(faker.name().firstName());
                }
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            System.out.println("El hilo se ha interrumpido.");
        }


    }
}
