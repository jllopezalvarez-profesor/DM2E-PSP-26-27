package es.jllopezalvarez.psp.ut02hilos.ejercicios.ejercicio09;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static final int NUM_JUGADORES = 10;
    public static final int MAXIMO_NUMERO_ADIVINAR = 10;
    private static final int TIEMPO_DESCANSO_JUGADORES = 1;

    public static void main(String[] args) throws InterruptedException {
        Arbitro arbitroCompartido = new Arbitro(NUM_JUGADORES, MAXIMO_NUMERO_ADIVINAR);

        List<Jugador> jugadores = new ArrayList<>(NUM_JUGADORES);

        for (int numJugador = 1; numJugador <= NUM_JUGADORES; numJugador++) {
            Jugador jugador = new Jugador(numJugador, MAXIMO_NUMERO_ADIVINAR, arbitroCompartido, TIEMPO_DESCANSO_JUGADORES );
            jugador.start();
            jugadores.add(jugador);
        }

        // Esperar a que los jugadores terminen
        for (Jugador jugador : jugadores) {
            jugador.join();
        }
    }
}
