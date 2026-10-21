package org.example.model;

import java.util.ArrayList;
import java.util.List;

import static java.awt.AWTEventMulticaster.add;
import static java.awt.geom.Path2D.contains;

public class Libro {
    private String titulo;
    private List<Observer> listObservadores;
    private boolean disponibilidad;

    public Libro(String titulo) {
        this.titulo = titulo;
        this.listObservadores = new ArrayList<>();
        this.disponibilidad = false;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {

    }

    public void suscribir(Observer observador) {
        if (observador != null && !listObservadores.contains(observador)) {
            listObservadores.add(observador);
        }
    }

    public void cancelar(Observer o) {
        listObservadores.remove(o);
    }

    public void devolver() {
        this.disponibilidad = true;
        notificar();
    }

    private void notificar() {
        for (Observer observador : listObservadores) {
            observador.actualizar(this);
        }
    }
}
