package es.jllopezalvarez.psp.ut02hilos.ejercicios.ejercicio14;

import java.util.Random;

public class Saco {
    private final Material material;
    private final int peso;

    private static final Random random = new Random();
    private static final int[] PESOS = {5,20,50};

    public Saco(Material material, int peso) {
        this.material = material;
        this.peso = peso;
    }


    public Material getMaterial() {
        return material;
    }

    public int getPeso() {
        return peso;
    }

    public static Saco crearAleatorio() {
        Material[] materiales = Material.values();
        Material materialAleatorio = materiales[random.nextInt(0, materiales.length)];
        int pesoAleatorio = PESOS[random.nextInt(0, PESOS.length)];
        return new Saco(materialAleatorio, pesoAleatorio);
    }

}
