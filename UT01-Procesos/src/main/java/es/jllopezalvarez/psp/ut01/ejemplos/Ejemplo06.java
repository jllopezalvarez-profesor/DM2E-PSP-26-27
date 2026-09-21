package es.jllopezalvarez.psp.ut01.ejemplos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

public class Ejemplo06 {

    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "dir");
        // Cambiar el directorio que quiero que se liste
        pb.directory(Path.of("C:\\").toFile());
        // Unificar errores y salida estándar en un único stream.
        // El warning se refiere a que ignormos el valor devuelto, que es el que adopta el objeto (si redirige o no)
        pb.redirectErrorStream();
        Process childProcess = pb.start();
        //try (var stream = new BufferedReader(new InputStreamReader(childProcess.getInputStream()))) {
        try (InputStreamReader isr = new InputStreamReader(childProcess.getInputStream());
             BufferedReader br = new BufferedReader(isr)) {
            String line;

            // Lectura adelantada sin compactar
//            line= br.readLine();
//            while (line != null){
//                line=br.readLine();
//            }


            // Compactado
            while ((line = br.readLine()) != null) {
                System.out.printf("El proceso hijo dice: '%s'.\n", line);
            }

            int returnValue = childProcess.waitFor();

            System.out.printf("El listado de directorio ha terminado con código %d.\n", returnValue);
        }
    }
}
