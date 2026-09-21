package es.jllopezalvarez.psp.ut01.ejemplos;

import java.io.File;
import java.io.IOException;

public class Ejemplo05 {
    public static void main(String[] args) {
        try {

            String[] command = {"ping", "-n", "10", "www.google.com"}; // Hace los ping y al terminar devuelve el control, con código 0

            ProcessBuilder pb = new ProcessBuilder(command);
            Process process = pb.start();

            int controlCount = 0;
            while (process.isAlive()){
                Thread.sleep(1000);
                controlCount++;
                System.out.printf("El proceso hijo sigue vivo, lo he comprobado %d veces.\n", controlCount);
            }

            //int result = process.waitFor();
            //System.out.printf("El proceso ha devuelto un código %d.\n", result);
        } catch (IOException e) {
            throw new RuntimeException("Error, el programa principal se falló en E/S.", e);
        } catch (InterruptedException e) {
            throw new RuntimeException("Error, el programa principal se interrumpió mientras se esperaba al proceso.", e);
        }
    }
}
