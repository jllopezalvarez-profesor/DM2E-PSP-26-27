package es.jllopezalvarez.psp.ut02hilos.ejemplos.adicionales.ejemplo07waitnotify;

public class Ejemplo0701ErrorSiNoEnSync {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Inicio del programa...");

        // Crear un objeto para usarlo como "candado"
        Object object = new Object();

        // Intentar llamar a wait sobre el objeto
        // No es un error de compilación, pero si de ejecución.
        // Lanza IllegalMonitorStateException, con el mensaje "current thread is not owner"
        object.wait();

        // Esto pasa porque hasta que no se hace un synchronized(object) no se "posee" el monitor.
        // El hilo actual no lo ha hecho, así que se produce el error.

        System.out.println("Fin del programa...");
    }


}
