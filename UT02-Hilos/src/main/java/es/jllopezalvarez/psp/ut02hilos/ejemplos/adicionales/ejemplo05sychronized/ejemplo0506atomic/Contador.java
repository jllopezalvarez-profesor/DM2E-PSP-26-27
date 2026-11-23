package es.jllopezalvarez.psp.ut02hilos.ejemplos.adicionales.ejemplo05sychronized.ejemplo0506atomic;

public class Contador {
    private int contador = 0;

    public int getContador() {
        return contador;
    }

    public void incrementar() {
        contador++;
    }
}
