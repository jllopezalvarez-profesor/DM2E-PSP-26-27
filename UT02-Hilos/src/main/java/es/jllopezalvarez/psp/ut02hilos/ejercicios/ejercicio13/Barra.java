package es.jllopezalvarez.psp.ut02hilos.ejercicios.ejercicio13;

import java.util.LinkedList;
import java.util.Queue;

public class Barra {
    private final int tamanioMaximoBarra;
    private final Queue<String> barra = new LinkedList<>();

    public Barra(int tamanioMaximoBarra) {
        this.tamanioMaximoBarra = tamanioMaximoBarra;
    }

    public synchronized void ponerPlato(String nombrePlato, String nombreCocinero)
            throws InterruptedException {
        // Mirar si la barra está llena y esperar mientras no haya sitio
        while (barra.size() == this.tamanioMaximoBarra) {
            System.out.printf("El cocinero %s para porque no hay espacio en la barra.%n", nombreCocinero);
            // barra.wait();
            this.wait();
        }

        // Añadimos plato a la barra
        barra.add(nombrePlato);

        //barra.notifyAll();
        this.notifyAll();
    }

    public synchronized String cogerPlato(String nombreCamarero)
            throws InterruptedException {
        while (barra.isEmpty()){
            System.out.printf("El camarero %s para porque no hay platos en la barra.%n", nombreCamarero);
            // barra.wait();
            this.wait();
        }

        String plato = barra.remove();

        // barra.notifyAll();
        this.notifyAll();

        return plato;
    }

}
