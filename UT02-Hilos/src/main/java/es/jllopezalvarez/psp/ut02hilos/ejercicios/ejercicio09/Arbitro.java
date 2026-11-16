package es.jllopezalvarez.psp.ut02hilos.ejercicios.ejercicio09;

import java.util.Random;

public class Arbitro {
    private final int numJugadores;
    private final int numeroSecreto;
    private int proximoJugador;
    private boolean numeroEncontrado;


    public Arbitro(int numJugadores, int maximoNumeroAdivinar) {
        this.numJugadores = numJugadores;
        this.numeroSecreto = new Random().nextInt(maximoNumeroAdivinar) + 1;
        this.proximoJugador = 1;
        this.numeroEncontrado = false;

        // Mostrar el número secreto en pantalla para que podamos verificar que funciona bien
        System.out.printf("[Árbitro] El número secreto es el %s.%n", this.numeroSecreto);
    }

    public synchronized boolean juegoFinalizado() {
        return this.numeroEncontrado;
    }

    public synchronized boolean meToca(int numJugador) {
        return this.proximoJugador == numJugador;
    }

    public synchronized void probarSuerte(int numJugador, int numero) {
        if (!juegoFinalizado()) {
            if (numero == numeroSecreto) {
                System.out.printf("El jugador %s ha adivinado el número (%s). Se termina el juego.%n", numJugador, numero);
                numeroEncontrado = true;
                return;
            }

            System.out.printf("El jugador %s ha intentado el número %s.%n", numJugador, numero);


            // Avanzamos al siguiente jugador
            // Lo hacemos usando aritmética modular
            proximoJugador = (proximoJugador % numJugadores) + 1;
        }
    }


}
