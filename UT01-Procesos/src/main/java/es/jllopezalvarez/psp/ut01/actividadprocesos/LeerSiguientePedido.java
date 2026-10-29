package es.jllopezalvarez.psp.ut01.actividadprocesos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class LeerSiguientePedido {

    private static final String NOMBRE_FICHERO_PEDIDOS = "pedidos.txt";

    private static boolean noHayMasPedidos = true;
    private static int numUltimoPedido = 0;

    public static void main(String[] args) throws IOException, InterruptedException {




        while (noHayMasPedidos) {

            var pedidosEnFichero = leerTodosPedidosEnFichero();
            System.out.println(pedidosEnFichero);

            int finalNumUltimoPedido = numUltimoPedido;
            Optional<Pedido> pedidoActual= pedidosEnFichero
                    .stream()
                    .filter(pedido -> pedido.getNumero() > finalNumUltimoPedido)
                    .findFirst();

            if (pedidoActual.isPresent()) {
                int numPedidoActual = pedidoActual.orElseThrow().getNumero();
                System.out.println(pedidoActual);
                System.out.printf("Encontrado el pedido %s\n", numPedidoActual);
                // Procesar el pedido
                // Cambio el número de pedido actual
                numUltimoPedido = numPedidoActual;
            } else {
                System.out.printf("No parece que haya un pedido nuevo aún.");
            }

            Thread.sleep(1000);


        }


    }

    private static List<Pedido> leerTodosPedidosEnFichero() throws IOException {
        // Leer todas las líneas
        var lineas = Files.readAllLines(Path.of(NOMBRE_FICHERO_PEDIDOS));

        if (lineas.isEmpty()) {
            return Collections.emptyList();
        }

        boolean hayMarcaDeFin = lineas.getLast().equals("-----");
        if (hayMarcaDeFin) {
            lineas.removeLast();
        }

        var pedidos= lineas.stream().map(linea -> {
            var partes = linea.split(";");
            return new Pedido(Integer.parseInt(partes[0]), partes[1]);
        }).toList();

        int maxIdPedido = pedidos.stream().mapToInt(Pedido::getNumero).max().getAsInt();

        noHayMasPedidos = numUltimoPedido == maxIdPedido && hayMarcaDeFin;


        return pedidos;
    }


}
