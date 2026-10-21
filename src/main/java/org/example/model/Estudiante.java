package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Estudiante {
    private String nombre;
    private final List<String> notificadores = new ArrayList<>();

public Estudiante(String nombre) {
    this.nombre = nombre;
}

public String getNombre() {
    return nombre;
}

public void recibir(String mensaje) {
    notificadores.add(mensaje);
    System.out.println("[Notificación] " + mensaje);
}

public void verNotificaciones() {
    System.out.println("Notificaciones de " + nombre + ":");
    if (notificadores.isEmpty()) {
        System.out.println("  (sin notificaciones)");
    }
    for (String m : notificadores) {
        System.out.println("  - " + m);
    }
}
}

