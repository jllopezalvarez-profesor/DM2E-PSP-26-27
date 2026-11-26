package es.jllopezalvarez.psp.ut02hilos.ejercicios.ejercicio14;

public class Productor extends Thread {

    private final Cinta cinta;

    public Productor(String name, Cinta cinta) {
        super(name);
        this.cinta = cinta;
    }

    @Override
    public void run() {
        try {
            while(!this.isInterrupted()) {
                Saco sacoAleatorio = Saco.crearAleatorio();
                cinta.ponerSaco(sacoAleatorio, this.getName());
                Thread.sleep(500);
            }
            System.out.printf("El productor %s finaliza porque ha sido interrumpido (con flag).%n", this.getName());
        } catch (InterruptedException e) {
            System.out.printf("El productor %s finaliza porque ha sido interrumpido (con interrupción).%n", this.getName());
        }

    }
}
