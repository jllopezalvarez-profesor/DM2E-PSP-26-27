package es.jllopezalvarez.psp.ut01.ejercicios.ejercicio04d;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Consumidor {

    public static final int SLEEP_TIME = 100;

    public static void main(String[] args) {
        boolean finEncontrado = false;

        while (!finEncontrado) {
            try {

                // Obtener un objeto File que "apunta" al fichero.
                // El fichero teóricamente solo existirá si otro proceso que entra en la región crítica lo ha creado.
                File lock = new File("lock.txt");

                List<String> lineas;

                try (FileOutputStream lockFos = new FileOutputStream(lock);
                     FileChannel lockChannel = lockFos.getChannel();
                     FileLock fileLock = lockChannel.lock()) {

                    lineas = Files.readAllLines(Path.of(Productor.PATH_FICHERO));
                }


                if (!lineas.isEmpty()) {
                    String ultimaLinea = lineas.getLast();
                    finEncontrado = ultimaLinea.equals("FIN");
                    System.out.printf("He leído del fichero: '%s'\n", ultimaLinea);
                }
                Thread.sleep(SLEEP_TIME);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
