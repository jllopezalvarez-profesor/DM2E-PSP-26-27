package es.jllopezalvarez.psp.ut01.actividadprocesos;


import java.util.List;
import java.util.Objects;

public final class LecturaFichero {
    private final List<Pedido> pedidos;
    private final boolean hayMarcaDeFin;

    public LecturaFichero(List<Pedido> pedidos, boolean hayMarcaDeFin) {
        this.pedidos = pedidos;
        this.hayMarcaDeFin = hayMarcaDeFin;
    }

    public List<Pedido> pedidos() {
        return pedidos;
    }

    public boolean hayMarcaDeFin() {
        return hayMarcaDeFin;
    }

    @Override
    public String toString() {
        return "LecturaFichero[" +
                "pedidos=" + pedidos + ", " +
                "hayMarcaDeFin=" + hayMarcaDeFin + ']';
    }
}