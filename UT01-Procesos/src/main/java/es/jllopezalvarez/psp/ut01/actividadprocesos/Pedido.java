package es.jllopezalvarez.psp.ut01.actividadprocesos;

public class Pedido {
    private final int numero;
    private final String nombre;

    public Pedido(int numero, String nombre) {
        this.numero = numero;
        this.nombre = nombre;
    }

    public int getNumero() {
        return numero;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "numero=" + numero +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
