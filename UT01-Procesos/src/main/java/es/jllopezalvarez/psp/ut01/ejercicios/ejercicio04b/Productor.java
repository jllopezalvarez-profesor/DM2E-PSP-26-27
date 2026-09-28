package es.jllopezalvarez.psp.ut01.ejercicios.ejercicio04b;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

                // Obtener un objeto File que "apunta" al fichero.
                // El fichero teóricamente solo existirá si otro proceso que entra en la región crítica lo ha creado.
                File lock = new File("lock.txt");

                // Comprobar si no está bloqueado por otro (File.exists)
                while(lock.exists()){
                    // si existe es que alguien ha entrado en la RC
                    // Espera activa hasta que esté disponible.
                    Thread.sleep(100);
                }

                // Bloquearlo para mí (File.createNewFile)
                lock.createNewFile();

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

                // Liberar el bloqueo (File.delete)
                lock.delete();

                // Dormimos para que de tiempo al consumidor a mirar el fichero
                Thread.sleep(500);
            }
            try (var outputStream = new FileWriter(PATH_FICHERO, true)) {
                outputStream.write("FIN\n");
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
