package es.jllopezalvarez.psp.ut02hilos.ejemplos.clase.ejemplo05sinchronizedmethod;

import net.datafaker.Faker;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentMap;

public class GeneradorNombres extends Thread {

    ConcurrentMap

    private final Faker faker = new Faker(Locale.forLanguageTag("ES-es"));

    private final List<String> nombres;

    public GeneradorNombres(List<String> nombres) {
        this.nombres = nombres;
    }


    @Override
    public void run() {

        try {
            while(!Thread.interrupted()){
                aniadirNombre();
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            System.out.println("El hilo se ha interrumpido.");
        }


    }

    /*
    * Esto no funciona porque como es equivalente a
    * synchronized(this)
    * cada hilo bloquea usando un candado distinto.
    * No comparten el candado, como si pasa en el ejemplo 04.
    * */
    private synchronized void aniadirNombre() {
        nombres.add(faker.name().firstName());
    }
}
