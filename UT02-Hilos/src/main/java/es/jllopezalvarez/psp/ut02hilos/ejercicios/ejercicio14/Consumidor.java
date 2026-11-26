package es.jllopezalvarez.psp.ut02hilos.ejercicios.ejercicio14;

public class Consumidor extends Thread {
    private final Cinta cinta;

    public Consumidor(String name, Cinta cinta) {
        super(name);
        this.cinta = cinta;
    }

    @Override
    public void run() {
        try {
            while(!this.isInterrupted()) {
                // TODO: cambiar
                Thread.sleep(500);
            }
            System.out.printf("El productor %s finaliza porque ha sido interrumpido (con flag).%n", this.getName());
        } catch (InterruptedException e) {
            System.out.printf("El productor %s finaliza porque ha sido interrumpido (con interrupción).%n", this.getName());
        }

    }
}
