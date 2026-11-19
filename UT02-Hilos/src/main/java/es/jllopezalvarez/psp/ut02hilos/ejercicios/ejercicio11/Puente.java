package es.jllopezalvarez.psp.ut02hilos.ejercicios.ejercicio11;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Puente {
    public static final int TIEMPO_MINIMO_CRUCE = 1000;
    public static final int TIEMPO_MAXIMO_CRUCE = 2000;
    private final Semaphore semaforo;
    private final Random random = new Random();

    public Puente(int numCarriles) {
        semaforo = new Semaphore(numCarriles, true);
    }

    public void cruzar(String matricula) throws InterruptedException {
        System.out.printf("[Puente] El coche con matrícula %s quiere pasar.%n", matricula);

        // Para pasar hay que usar el semáforo
        // Relanzamos la excepción (throws en declaración) para que si el
        // hilo se interrumpe mientras espera paso, termine correctamente.
        semaforo.acquire();

        // TODO: Hacer control de InterruptedException

        // Pasa el puente. Lo simulamos con Thread.sleep
        System.out.printf("[Puente] El coche con matrícula %s está pasando.%n", matricula);

        try {
            Thread.sleep(random.nextInt(TIEMPO_MINIMO_CRUCE, TIEMPO_MAXIMO_CRUCE));

            // suimular interrupted exception
            if (random.nextInt(100) > 75){
                throw new InterruptedException();
            }
            System.out.printf("[Puente] El coche con matrícula %s ha pasado.%n", matricula);
        }
        catch (Exception e){
            System.out.printf("[Puente] El coche con matrícula %s ha tenido problemas y lo sacamos del puente.%n", matricula);
            throw e;
        }
        finally {
            // Tras pasar liberamos el uso del semáforo
            semaforo.release();
        }



    }

    public int getCarrilesLibres() {
        return semaforo.availablePermits();
    }
}
