package es.jllopezalvarez.psp.ut02hilos.ejemplos.adicionales.ejemplo08prodconsum.ejemplo0806notyfyvsnotifyall;

import java.util.List;

public interface NumberQueue {

    void add(Integer number) throws InterruptedException;

    Integer remove() throws InterruptedException;

    List<Integer> getNumbers();
}
