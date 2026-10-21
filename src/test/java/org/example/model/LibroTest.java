package org.example.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class LibroTest {
    private Libro libro;

    // Observer falso que cuenta cuántas veces lo notifican
    private static class ObserverEspia implements Observer {
        int veces = 0;
        Libro ultimoLibro = null;

        @Override
        public void actualizar(Libro libro) {
            veces++;
            ultimoLibro = libro;
        }
    }

    @BeforeEach
    void setUp() {
        libro = new Libro("Estructuras de Datos en Java");
    }

    // suscribir

    @Test
    void suscribir_observerSuscritoRecibeNotificacionAlDevolver() {
        // Arrange
        ObserverEspia observer = new ObserverEspia();
        libro.suscribir(observer);

        // Act
        libro.devolver();

        // Assert
        assertEquals(1, observer.veces);
    }

}
  
