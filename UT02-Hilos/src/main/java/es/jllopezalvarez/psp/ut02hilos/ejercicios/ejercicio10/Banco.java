package es.jllopezalvarez.psp.ut02hilos.ejercicios.ejercicio10;

public class Banco {
    private int saldo;
    private final long retrasoOperaciones;

    public Banco(int saldoInicial, long retrasoOperaciones) {
        this.saldo = saldoInicial;
        this.retrasoOperaciones = retrasoOperaciones;
    }

    public int ingresar(int numCliente, int cantidad) {
        // Los ingresos siempre funcionan
        System.out.printf("[Banco] El cliente %s está ingresando %s €.%n", numCliente, cantidad);
        synchronized (this) {
            saldo += cantidad;
        }
        return cantidad;
    }

    public synchronized int ingresarSincronizado(int numCliente, int cantidad) {
        // Los ingresos siempre funcionan
        System.out.printf("[Banco] El cliente %s está ingresando %s €.%n", numCliente, cantidad);
        saldo += cantidad;
        return cantidad;
    }

    public int retirar(int numCliente, int cantidad) throws InterruptedException {
        System.out.printf("[Banco] El cliente %s está intentando retirar %s €.%n", numCliente, cantidad);

        synchronized (this) {
            if (cantidad > this.saldo) {
                // System.out.printf("[Banco] El cliente %s no ha podido completar la operación por saldo insuficiente.%n", numCliente);
                return 0;
            }
            saldo -= cantidad;
        }

        Thread.sleep(retrasoOperaciones);
        return -cantidad;
    }

    public synchronized int retirarSincronizado(int numCliente, int cantidad) throws InterruptedException {
        System.out.printf("[Banco] El cliente %s está intentando retirar %s €.%n", numCliente, cantidad);
        if (cantidad > this.saldo) {
            System.out.printf("[Banco] El cliente %s no ha podido completar la operación por saldo insuficiente.%n", numCliente);
            return 0;
        }
        saldo -= cantidad;
        Thread.sleep(retrasoOperaciones);
        return -cantidad;
    }

    public synchronized int getSaldo() {
        return saldo;
    }
}
