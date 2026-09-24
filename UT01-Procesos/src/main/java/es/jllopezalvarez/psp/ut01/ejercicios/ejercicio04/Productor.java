package es.jllopezalvarez.psp.ut01.ejercicios.ejercicio04;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class Productor {
    public static final String PATH_FICHERO = "mensajes.txt";
    private static final long SLEEP_TIME = 250;
    private static final int LOOP_COUNT = 100;

    public static void main(String[] args) {
        try {
            Files.deleteIfExists(Path.of(PATH_FICHERO));

            for (int i = 1; i <= LOOP_COUNT; i++) {
                try (var outputStream = new FileWriter(PATH_FICHERO, true)) {
                    outputStream.write(LocalDateTime.now().toString());
                    outputStream.flush();
                    Thread.sleep(SLEEP_TIME);
                    outputStream.write(" - ");
                    outputStream.write(String.valueOf(i));
                    outputStream.write("\n");
                    outputStream.flush();
                    Thread.sleep(SLEEP_TIME);
                }
            }
            try (var outputStream = new FileWriter(PATH_FICHERO, true)) {
                outputStream.write("FIN\n");
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
