package es.jllopezalvarez.psp.ut01.actividadprocesos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class LeerSiguientePedido {

    private static final Path FICHERO_PEDIDOS = Path.of("pedidos.txt");
    private static final String MARCA_DE_FIN = "-----";
    private static final long ESPERA_MS = 1000;


    public static void main(String[] args) throws IOException, InterruptedException {

        int numUltimoPedido = 0; // Último que se procesó.
        boolean terminado = false; // Control del bucle.

        while (!terminado) {
            // Leer el fichero. Se leen pedidos, y se detecta marca de fin,
            // que se guardan en el objeto de tipo LecturaFichero
            LecturaFichero lectura = leerFichero();

            // Filtramos pedidos, para quedarnos con los nuevos, y obtenemos el menor de los nuevos
            int ultimo = numUltimoPedido; // Esto es necesario por cómo se ejecutan las expresiones Lambda.
            Optional<Pedido> siguiente = lectura.pedidos().stream()
                    .filter(p -> p.getNumero() > ultimo) // Lambda - Solo los posteriores al último.
                    .min(Comparator.comparingInt(Pedido::getNumero)); // Nos quedamos con el menor. Devuelve Optional.

            if (siguiente.isPresent()) {
                // Si hay siguiente se procesa.
                Pedido pedido = siguiente.get();
                procesarPedido(pedido);
                numUltimoPedido = pedido.getNumero();
            } else if (lectura.hayMarcaDeFin()) {
                // Si no había siguiente, y hay marca de fin, terminamos.
                terminado = true;
            } else {
                // Si no lo hay, pero no había marca, es que no se han guardado más en el fichero.
                System.out.println("No parece que haya un pedido nuevo aún.");
            }

            if (!terminado) {
                Thread.sleep(ESPERA_MS);
            }
        }

        System.out.printf("Fin: no hay más pedidos. Último procesado: %d%n", numUltimoPedido);
    }

    private static void procesarPedido(Pedido pedido) {
        System.out.printf("Encontrado el pedido %d: %s%n", pedido.getNumero(), pedido);
        // TODO: lógica real de procesado
    }

    /**
     * Lee el fichero y devuelve los pedidos y si se ha encontrado.
     */
    private static LecturaFichero leerFichero() throws IOException {
        // Si el otro proceso no ha creado aún el fichero, se devuelve una lista vacía de ficheros.
        if (Files.notExists(FICHERO_PEDIDOS)) {
            System.out.println("El fichero de pedidos aún no existe.");
            return new LecturaFichero(List.of(), false);
        }

        // Se lee el fichero, y se hace una copia sin líneas en blanco
        List<String> lineas = new ArrayList<>(Files.readAllLines(FICHERO_PEDIDOS).stream()
                .map(String::strip) // Limpiar espacios delante y detrás. Similar a trim, pero más moderno.
                .filter(linea -> !linea.isEmpty()) // Quitar líneas vacías.
                .toList());

        // Detectar marca de fin. Si se encuentra, quitarla porque no se puede procesar como pedido.
        boolean hayMarcaDeFin = !lineas.isEmpty() && lineas.getLast().equals(MARCA_DE_FIN);
        if (hayMarcaDeFin) {
            lineas.removeLast();
        }

        // Crear pedidos por línea
        List<Pedido> pedidos = lineas.stream()
                .map(LeerSiguientePedido::parsearLinea) // Convertir pedido en texto a objeto
                .filter(Objects::nonNull) // Quitar nulos, porque la conversión devuelve null si encuentra algún error
                .collect(Collectors.toList());

        // Devolver los datos obtenidos. Para evitar usar globales, se ha creado un nuevo objeto que aglutina
        // los pedidos leídos y si se ha encontrado el fin.
        return new LecturaFichero(pedidos, hayMarcaDeFin);
    }

    /**
     * Devuelve el pedido de la línea, o null si está mal formada (p. ej. a medio escribir).
     */
    private static Pedido parsearLinea(String linea) {
        String[] partes = linea.split(";", 2);
        if (partes.length < 2) {
            System.err.printf("Línea ignorada (formato incorrecto): %s%n", linea);
            return null;
        }
        try {
            return new Pedido(Integer.parseInt(partes[0].strip()), partes[1].strip());
        } catch (NumberFormatException e) {
            System.err.printf("Línea ignorada (número no válido): %s%n", linea);
            return null;
        }
    }
}
