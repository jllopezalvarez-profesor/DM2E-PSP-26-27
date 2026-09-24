package es.jllopezalvarez.psp.ut01.ejercicios.ejercicio04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Consumidor {

    public static final int SLEEP_TIME = 100;

    public static void main(String[] args) {
        boolean finEncontrado = false;

        while (!finEncontrado){
            try {
                List<String> lineas = Files.readAllLines(Path.of(Productor.PATH_FICHERO));
                if (!lineas.isEmpty()) {
                    String ultimaLinea = lineas.getLast();
                    finEncontrado= ultimaLinea.equals("FIN");
                    System.out.printf("He leído del fichero: '%s'\n", ultimaLinea);
                }
                Thread.sleep(SLEEP_TIME);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
