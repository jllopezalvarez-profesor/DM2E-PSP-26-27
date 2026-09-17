package es.jllopezalvarez.psp.ut01.ejemplos;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Ejemplo04 {
    public static void main(String[] args) {
        try {

            String[] command = {"ping", "-n", "10", "www.google.com"}; // Hace los 3 ping y al terminar devuelve el control, con código 0

            ProcessBuilder pb = new ProcessBuilder(command);
            pb.directory(new File("C:\\"));
            Process process = pb.start();
            boolean finished = process.waitFor(1500, TimeUnit.MILLISECONDS);

            if (!finished){ // Si no acabó en un segundo u medio
                process.destroy(); // Forzamos que el proceso muera.
            }

            int result = process.exitValue();
            System.out.printf("El proceso ha devuelto un código %d.\n", result);
        } catch (IOException e) {
            throw new RuntimeException("Error, el programa principal se falló en E/S.", e);
        } catch (InterruptedException e) {
            throw new RuntimeException("Error, el programa principal se interrumpió mientras se esperaba al proceso.", e);
        }
    }
}
