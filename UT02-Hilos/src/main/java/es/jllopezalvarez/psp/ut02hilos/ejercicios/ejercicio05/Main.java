package es.jllopezalvarez.psp.ut02hilos.ejercicios.ejercicio05;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final int NUM_SUMADORES = 1000;

    public static void main(String[] args) throws InterruptedException {
        List<Sumador> sumadores = new ArrayList<>(NUM_SUMADORES);

        // Crear los sumadores
        for(int i = 0; i<NUM_SUMADORES; i++){
            Sumador nuevoSumador = new Sumador();
            sumadores.add(nuevoSumador);
        }

        // Iniciar los sumadores
        for (Sumador sumador : sumadores) {
            sumador.start();
        }

        // Alternativa para iniciar sumadores
        // sumadores.forEach(Thread::start);

        // Esperar a que terminen
        for (Sumador sumador : sumadores) {
            sumador.join();
        }

        // Mostrar la suma de todos
        for (int i = 0; i < sumadores.size() ; i++) {
            Sumador sumador = sumadores.get(i);
//            System.out.printf("Suma del sumador %d: %d.\n", i, Sumador.getSuma());
        }

        // Mostrar suma total
        System.out.printf("La suma de todos los sumadores es: %s\n", Sumador.getSuma());
    }
}
