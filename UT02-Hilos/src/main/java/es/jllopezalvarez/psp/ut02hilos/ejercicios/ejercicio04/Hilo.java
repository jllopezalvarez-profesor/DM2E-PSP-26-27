package es.jllopezalvarez.psp.ut02hilos.ejercicios.ejercicio04;

public class Hilo implements Runnable   {
    private final String nombre;
    private final int numCiclos;
    private final long tiempoSuenio;

    public Hilo(String nombre, int numCiclos, long tiempoSuenio) {
        this.nombre = nombre;
        this.numCiclos = numCiclos;
        this.tiempoSuenio = tiempoSuenio;
    }

    @Override
    public void run() {
        for(int i=0; i<numCiclos; i++){
            System.out.printf("Soy el hilo '%s' y voy por el %d de %d\n", this.nombre, i, numCiclos);
            try {
                Thread.sleep(tiempoSuenio);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.printf("Soy '%s' y he terminado\n", this.nombre);

    }
}
