package es.jllopezalvarez.psp.ut02hilos.ejemplos.adicionales.ejemplo05sychronized.ejemplo0502volatile;

public class Contador {
    private volatile int contador = 0;

    public int getContador() {
        return contador;
    }

    public void incrementar() {
        contador++;
    }
}
