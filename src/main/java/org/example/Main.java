package org.example;

import org.example.model.Estudiante;
import org.example.model.Libro;
import org.example.model.Notificador;
import org.example.model.Observer;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Libro libro = new Libro("Magia");

        Estudiante ana = new Estudiante("Ana");
        Estudiante luis = new Estudiante("Luis");

        Observer notiAna = new Notificador(ana);
        Observer notiLuis = new Notificador(luis);

        System.out.println("=== Suscripciones ===");
        libro.suscribir(notiAna);
        libro.suscribir(notiLuis);
        System.out.println("Ana y Luis se suscribieron a \"" + libro.getTitulo() + "\"");

        System.out.println("\n=== Luis cancela su suscripción ===");
        libro.cancelar(notiLuis);
        System.out.println("Luis canceló su suscripción");

        System.out.println("\n=== Devolución del libro ===");
        libro.devolver();

        System.out.println("\n=== Notificaciones recibidas ===");
        ana.verNotificaciones();
        luis.verNotificaciones();


    }
}