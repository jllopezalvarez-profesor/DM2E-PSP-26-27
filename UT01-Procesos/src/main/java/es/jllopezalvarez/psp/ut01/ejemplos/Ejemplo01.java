package es.jllopezalvarez.psp.ut01.ejemplos;

import java.io.IOException;

public class Ejemplo01 {
    public static void main(String[] args) {
        try {
            // String[] command = {"cmd", "-c", "dir"};
            // String[] command = {"cmd", "-c", "ping -t www.google.com"}; // El ping no termina nunca
            // String[] command = {"cmd", "-c", "ping -n 5 www.google.com"}; // Aunque el ping termina, el CMD no
            // String[] command = {"ping", "-n", "3", "www.googleeee.com"}; // Devuelve código 1 porque no existe el dominio
            String[] command = {"ping", "-n", "3", "www.google.com"}; // Hace los 3 ping y al terminar devuelve el control, con código 0
            Process process = Runtime.getRuntime().exec(command);

            int result = process.waitFor();

            System.out.printf("El proceso ha devuelto un código %d.\n", result);


        } catch (IOException e) {
            throw new RuntimeException("Error, el programa principal se falló en E/S.", e);
        } catch (InterruptedException e) {
            throw new RuntimeException("Error, el programa principal se interrumpió mientras se esperaba al proceso.", e);
        }
    }
}
