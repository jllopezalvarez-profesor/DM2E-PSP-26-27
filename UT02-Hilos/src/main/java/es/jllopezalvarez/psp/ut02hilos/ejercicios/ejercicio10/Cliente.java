package es.jllopezalvarez.psp.ut02hilos.ejercicios.ejercicio10;

import java.util.Random;

public class Cliente extends Thread {
    private static final long TIEMPO_MIN_DESCANSO_CLIENTES = 10;
    private static final long TIEMPO_MAX_DESCANSO_CLIENTES = 20;
    private static final long NUM_OPERACIONES = 100;

    private final int numCliente;
    private final Banco banco;

    private int totalAportado = 0;

    private final Random random = new Random();

    public Cliente(int numCliente, Banco banco) {
        this.numCliente = numCliente;
        this.banco = banco;
    }

    @Override
    public void run() {
        try {
            for (int numOperacion = 1; numOperacion <= NUM_OPERACIONES && !this.isInterrupted(); numOperacion++) {
                // decidir si es ingreso. Si no es, es retirada.
                boolean esIngreso = random.nextBoolean();
                // Elegir cantidad aleatoria
                int cantidad = random.nextInt(10, 200);
                // Realizar la operación
                if (esIngreso) {
                    cantidad = banco.ingresar(this.numCliente, cantidad);
                } else {
                    cantidad = banco.retirar(this.numCliente, cantidad);
                }
                // Acumular la cantidad (siempre se suma porque retirar devuelve negativo si funciona)
                totalAportado+= cantidad;

                // Descansa un rato
                Thread.sleep(random.nextLong(TIEMPO_MIN_DESCANSO_CLIENTES, TIEMPO_MAX_DESCANSO_CLIENTES));
            }
        } catch (InterruptedException e) {
            System.out.printf("El cliente %d ha sido interrumpido.%n", this.numCliente);
        }


    }

    public int getTotalAportado() {
        return totalAportado;
    }
}
