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

    @Test
    void suscribir_mismoObserverDosVecesSoloSeNotificaUnaVez() {
        // Arrange
        ObserverEspia observer = new ObserverEspia();
        libro.suscribir(observer);
        libro.suscribir(observer);

        // Act
        libro.devolver();

        // Assert
        assertEquals(1, observer.veces);
    }

    @Test
    void suscribir_variosObserversTodosSonNotificados() {
        // Arrange
        ObserverEspia observer1 = new ObserverEspia();
        ObserverEspia observer2 = new ObserverEspia();
        libro.suscribir(observer1);
        libro.suscribir(observer2);

        // Act
        libro.devolver();

        // Assert
        assertEquals(1, observer1.veces);
        assertEquals(1, observer2.veces);
    }

    @Test
    void suscribir_observerNuloSeIgnoraYNoLanzaExcepcion() {
        // Arrange
        libro.suscribir(null);

        // Act & Assert
        assertDoesNotThrow(() -> libro.devolver());
    }

    //cancelar

    @Test
    void cancelar_observerCanceladoNoRecibeNotificacion() {
        // Arrange
        ObserverEspia observer = new ObserverEspia();
        libro.suscribir(observer);
        libro.cancelar(observer);

        // Act
        libro.devolver();

        // Assert
        assertEquals(0, observer.veces);
    }

    @Test
    void cancelar_soloDejaDeNotificarseAlCancelado() {
        // Arrange
        ObserverEspia cancelado = new ObserverEspia();
        ObserverEspia activo = new ObserverEspia();
        libro.suscribir(cancelado);
        libro.suscribir(activo);
        libro.cancelar(cancelado);

        // Act
        libro.devolver();

        // Assert
        assertEquals(0, cancelado.veces);
        assertEquals(1, activo.veces);
    }

    @Test
    void cancelar_observerNoSuscritoNoLanzaExcepcion() {
        // Arrange
        ObserverEspia observer = new ObserverEspia();

        // Act & Assert
        assertDoesNotThrow(() -> libro.cancelar(observer));
    }

    //devolver
    @Test
    void devolver_notificaConElMismoLibro() {
        // Arrange
        ObserverEspia observer = new ObserverEspia();
        libro.suscribir(observer);

        // Act
        libro.devolver();

        // Assert
        assertSame(libro, observer.ultimoLibro);
    }

    @Test
    void devolver_sinSuscriptoresNoLanzaExcepcion() {
        // Act & Assert
        assertDoesNotThrow(() -> libro.devolver());
    }

    @Test
    void devolver_cadaLlamadaNotificaALosSuscritos() {
        // Arrange
        ObserverEspia observer = new ObserverEspia();
        libro.suscribir(observer);

        // Act
        libro.devolver();
        libro.devolver();

        // Assert
        assertEquals(2, observer.veces);
    }

    @Test
    void devolver_observerSuscritoDespuesDeCancelarVuelveAserNotificado() {
        // Arrange
        ObserverEspia observer = new ObserverEspia();
        libro.suscribir(observer);
        libro.cancelar(observer);
        libro.suscribir(observer);

        // Act
        libro.devolver();

        // Assert
        assertEquals(1, observer.veces);
    }
}
  
