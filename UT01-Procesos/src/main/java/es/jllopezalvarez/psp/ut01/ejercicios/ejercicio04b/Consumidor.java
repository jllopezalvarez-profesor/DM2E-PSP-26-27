package es.jllopezalvarez.psp.ut01.ejercicios.ejercicio04b;

import java.io.File;
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

                List<String> lineas = Files.readAllLines(Path.of(Productor.PATH_FICHERO));

                // Liberar bloqueo
                lock.delete();

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
