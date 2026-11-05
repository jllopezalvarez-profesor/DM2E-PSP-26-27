package es.jllopezalvarez.psp.ut02hilos.ejemplos.clase.ejemplo04sinchronized;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) throws InterruptedException {

        List<String> nombres = new ArrayList<>();


        List<Thread> hilos = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Thread hilo = new GeneradorNombres(nombres);
            hilo.start();
            hilos.add(hilo);
        }

        long tiempoFinal = System.currentTimeMillis() + 10_000;

        while(System.currentTimeMillis() < tiempoFinal){
            Thread.sleep(500);
            synchronized (nombres) {
                System.out.printf("Datos en la lista: %s\n", nombres);
            }
        }

        for (Thread hilo : hilos) {
            hilo.interrupt();
        }

        for (Thread hilo : hilos) {
            hilo.join();
        }






    }
}
