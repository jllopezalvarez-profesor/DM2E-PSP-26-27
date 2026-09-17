package es.jllopezalvarez.psp.ut01.ejemplos;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Ejemplo02 {
    public static void main(String[] args) {
        try {
            String[] command = {"ping", "-n", "10", "www.google.com"};
            Process process = Runtime.getRuntime().exec(command);

            boolean finished = process.waitFor(10, TimeUnit.SECONDS);

            if (finished){
                System.out.println("El proceso terminó");
                int result = process.exitValue();
                System.out.printf("El proceso ha devuelto un código %d.\n", result);
            } else  {
                System.out.println("Me he cansado de esperar a que termine");
            }

//            int result = process.exitValue(); // Si llamamos a esto antes de que acabe el proceso falla
//            System.out.printf("El proceso ha devuelto un código %d.\n", result);


        } catch (IOException e) {
            throw new RuntimeException("Error, el programa principal se falló en E/S.", e);
        } catch (InterruptedException e) {
            throw new RuntimeException("Error, el programa principal se interrumpió mientras se esperaba al proceso.", e);
        }
    }
}
