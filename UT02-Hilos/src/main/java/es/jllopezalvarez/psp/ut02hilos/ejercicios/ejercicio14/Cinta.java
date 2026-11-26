package es.jllopezalvarez.psp.ut02hilos.ejercicios.ejercicio14;

import java.util.Queue;

public class Cinta {
    private final Queue<Saco> cinta;
    private final int numMaximoSacos;
    private final int numMaxMismoTipo;
    private final int pesoMaximo;

    public Cinta(Queue<Saco> cinta, int numMaximoSacos, int numMaxMismoTipo, int pesoMaximo) {
        this.cinta = cinta;
        this.numMaximoSacos = numMaximoSacos;
        this.numMaxMismoTipo = numMaxMismoTipo;
        this.pesoMaximo = pesoMaximo;
    }

    public synchronized void ponerSaco(Saco saco, String nombreProductor) throws InterruptedException {
        System.out.printf("El productor %s quiere poner un saco de %s de %d kilos.%n",
                nombreProductor, saco.getMaterial(), saco.getPeso());

        while (!seAdmiteSaco(saco)) {
            System.out.printf("El productor %s tiene que esperar porque el saco no cabe.%n", nombreProductor);
            this.wait();
        }

        this.notifyAll();
    }


    public synchronized Saco quitarSaco(String nombreConsumidor) throws InterruptedException {
        System.out.printf("El consumidor %s quiere obtener un saco de la cinta.%n", nombreConsumidor);
        while (this.cinta.isEmpty()) {
            System.out.printf("El consumidor %s tiene que esperar porque no hay sacos.%n", nombreConsumidor);
            this.wait();
        }
        Saco saco = this.cinta.remove();
        this.notifyAll();
        return saco;
    }


    private boolean seAdmiteSaco(Saco saco) {
        if ( this.cinta.size() >= numMaximoSacos)
            return false;

        long numSacosIguales = this.cinta.stream().filter(sacoEnCinta->sacoEnCinta.getMaterial() == saco.getMaterial()).count();
        if (numSacosIguales >= this.numMaxMismoTipo)
            return false;

        int pesoSacosEnCinta = this.cinta.stream().mapToInt(Saco::getPeso).sum();
        if ((pesoSacosEnCinta + saco.getPeso()) >= this.pesoMaximo)
            return false;

        return true;
    }
}
