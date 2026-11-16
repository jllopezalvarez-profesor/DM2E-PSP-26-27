package es.jllopezalvarez.psp.ut02hilos.ejercicios.ejercicio09;

import java.util.Random;

public class Jugador extends Thread {
    private final int numJugador;
    private final int maximoNumeroSecreto;
    private final Arbitro arbitro;

    private final Random random = new Random();
    private final int tiempoDescanso;

    public Jugador(int numJugador, int maximoNumeroSecreto, Arbitro arbitro, int tiempoDescanso) {
        this.numJugador = numJugador;
        this.maximoNumeroSecreto = maximoNumeroSecreto;
        this.arbitro = arbitro;
        this.tiempoDescanso = tiempoDescanso;
    }

    @Override
    public void run() {
        try {
            System.out.printf("[Jugador %s] Empiezo a jugar.%n", this.numJugador);
            while (!this.isInterrupted() && !arbitro.juegoFinalizado()) {

                if (arbitro.meToca(this.numJugador)) {
                    int numeroPrueba = random.nextInt(maximoNumeroSecreto) + 1;
                    arbitro.probarSuerte(this.numJugador, numeroPrueba);
                }

                Thread.sleep(tiempoDescanso);

            }
            System.out.printf("[Jugador %s] El juego ha finalizado.%n", this.numJugador);

        } catch (InterruptedException e) {
            System.out.printf("El jugador %s ha sido interrumpido con excepción.%n", numJugador);
        }


    }
}
