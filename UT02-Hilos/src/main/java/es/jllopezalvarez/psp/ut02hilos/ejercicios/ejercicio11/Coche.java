package es.jllopezalvarez.psp.ut02hilos.ejercicios.ejercicio11;

public class Coche extends Thread {

    private final String matricula;
    private final Puente puente;

    public Coche(String matricula, Puente puente) {
        this.matricula = matricula;
        this.puente = puente;
    }

    @Override
    public void run() {
        // En este caso no hay bucle, pero puede haber InterruptedException
        try {
            System.out.printf("[Coche] Soy el coche %s y me dirijo al puente.%n", this.matricula);
            puente.cruzar(this.matricula);
        } catch (InterruptedException e) {
            System.out.printf("[Coche] El coche %s ha sido interrumpido.%n", this.matricula);
        }
    }
}
