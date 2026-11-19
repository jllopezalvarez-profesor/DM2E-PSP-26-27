package es.jllopezalvarez.psp.ut02hilos.ejercicios.ejercicio10;

import java.util.ArrayList;
import java.util.List;

public class Main {


    private static final int SALDO_INICIAL_BANCO = 0;
    private static final long RETRASO_OPERACION_BANCO = 2;
    private static final int NUM_CLIENTES = 1000;

    public static void main(String[] args) throws InterruptedException {
        Banco banco = new Banco(SALDO_INICIAL_BANCO, RETRASO_OPERACION_BANCO);

        List<Cliente> clientes = new ArrayList<>(NUM_CLIENTES);

        for (int numCliente = 1; numCliente <= NUM_CLIENTES; numCliente++) {
            Cliente cliente = new Cliente(numCliente, banco);
            cliente.start();
            clientes.add(cliente);
        }

        for (Cliente cliente : clientes) {
            cliente.join();
        }

        // Verificar datos
        int aportadoClientes = clientes.stream().mapToInt(Cliente::getTotalAportado).sum();

        System.out.printf("[Main] Programa terminado - verificando datos.%n");
        System.out.printf("[Main] Saldo inicial del banco: %s€.%n", SALDO_INICIAL_BANCO);
        System.out.printf("[Main] Saldo final del banco: %s€.%n", banco.getSaldo());
        System.out.printf("[Main] Total aportado por clientes: %s€.%n", aportadoClientes);
        System.out.printf("[Main] Suma de inicial y aportaciones: %s€.%n", SALDO_INICIAL_BANCO + aportadoClientes);
    }


}
