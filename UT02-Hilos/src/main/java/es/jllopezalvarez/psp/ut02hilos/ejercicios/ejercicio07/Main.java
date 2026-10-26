package es.jllopezalvarez.psp.ut02hilos.ejercicios.ejercicio07;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static final int SIESTA_MAIN = 2000;
    private static final int NUM_HILOS = 5;
    private static final long MIN_SIESTA = 250;
    private static final long MAX_SIESTA = 500;
    public static final int TIEMPO_ESPERA_ENTRE_INTERRUPTS = 1000;

    private static Random aleatorios = new Random();

    private static List<Thread> hilos = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Voy a crear los hilos.");

        // Crear hilos
        for(int numHilo = 1; numHilo <= NUM_HILOS; numHilo++){
            hilos.add(new Thread(new Contador("Contador" + numHilo, aleatorios.nextLong(MIN_SIESTA, MAX_SIESTA))));
        }

        System.out.println("Voy a arrancar los hilos.");

        // Arrancar los hilos
        for(Thread hilo : hilos){
            hilo.start();
        }

        System.out.println("Voy a esperar un poco antes de parar.");

        // Esperar un poco
        Thread.sleep(SIESTA_MAIN);

        System.out.println("Voy a detener a los contadores.");

        // Cada segundo detener un hilo
        for(Thread hilo: hilos){
            System.out.println("Voy a detener a un contador.");
            hilo.interrupt();
            Thread.sleep(TIEMPO_ESPERA_ENTRE_INTERRUPTS);
        }

        System.out.println("Espero a que acaben los contadores.");

        // Esperar a que todos acaben
        for(Thread hilo:hilos){
            hilo.join();
        }

        System.out.println("Ya han terminado todos los contadores.");


    }

}
