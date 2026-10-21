package org.example.model;

public class Notificador implements Observer{
    private final Estudiante estudiante;

    public Notificador(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    @Override
    public void actualizar(Libro libro) {
        estudiante.recibir("Hola " + estudiante.getNombre()
                + ": el libro '" + libro.getTitulo() + "' ya está disponible.");
    }



}
