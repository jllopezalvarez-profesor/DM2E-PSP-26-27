package es.jllopezalvarez.psp.ut01.ejercicios.ejercicio04d;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
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


                // El fichero puede existir aunque no esté bloqueado.
                File lock = new File("lock.txt");


                try(FileOutputStream lockFos = new FileOutputStream(lock);
                    FileChannel lockChannel = lockFos.getChannel();
                    FileLock fileLock = lockChannel.lock()) {

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
