package com.centroanimal.ms_notificaciones;

import com.centroanimal.ms_notificaciones.model.Notificacion;
import com.centroanimal.ms_notificaciones.repository.NotificacionRepository;
import com.centroanimal.ms_notificaciones.service.NotificacionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class NotificacionServiceTest {

    @Autowired
    private NotificacionService notificacionService;

    @MockBean
    private NotificacionRepository notificacionRepository;

    @Test
    @DisplayName("Debe retornar lista de notificaciones")
    void testFindAll() {
        Notificacion notificacion = new Notificacion(1L, 1L, "Visita confirmada", "VISITA", LocalDate.now());
        when(notificacionRepository.findAll()).thenReturn(List.of(notificacion));

        List<Notificacion> resultado = notificacionService.findAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    @DisplayName("Debe retornar notificacion por ID")
    void testFindById() {
        Notificacion notificacion = new Notificacion(1L, 1L, "Visita confirmada", "VISITA", LocalDate.now());
        when(notificacionRepository.findById(1L)).thenReturn(Optional.of(notificacion));

        Notificacion resultado = notificacionService.findById(1L);

        assertNotNull(resultado);
        assertEquals("VISITA", resultado.getTipoNotificacion());
    }

    @Test
    @DisplayName("Debe crear notificacion correctamente")
    void testSave() {
        Notificacion notificacion = new Notificacion(null, 1L, "Visita confirmada", "VISITA", null);
        Notificacion notificacionGuardada = new Notificacion(1L, 1L, "Visita confirmada", "VISITA", LocalDate.now());
        when(notificacionRepository.save(notificacion)).thenReturn(notificacionGuardada);

        Notificacion resultado = notificacionService.save(notificacion);

        assertNotNull(resultado);
        assertEquals("VISITA", resultado.getTipoNotificacion());
    }

    @Test
    @DisplayName("Debe lanzar excepcion si idUsuario es nulo")
    void testSaveSinUsuario() {
        Notificacion notificacion = new Notificacion(null, null, "Visita confirmada", "VISITA", null);

        assertThrows(RuntimeException.class, () -> notificacionService.save(notificacion));
    }

    @Test
    @DisplayName("Debe lanzar excepcion si mensaje es nulo")
    void testSaveSinMensaje() {
        Notificacion notificacion = new Notificacion(null, 1L, null, "VISITA", null);

        assertThrows(RuntimeException.class, () -> notificacionService.save(notificacion));
    }

    @Test
    @DisplayName("Debe lanzar excepcion si tipo de notificacion es invalido")
    void testSaveTipoInvalido() {
        Notificacion notificacion = new Notificacion(null, 1L, "Mensaje", "INVALIDO", null);

        assertThrows(RuntimeException.class, () -> notificacionService.save(notificacion));
    }

    @Test
    @DisplayName("Debe eliminar notificacion por ID")
    void testEliminar() {
        doNothing().when(notificacionRepository).deleteById(1L);

        notificacionService.eliminar(1L);

        verify(notificacionRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Debe retornar notificaciones por tipo")
    void testFindByTipoNotificacion() {
        Notificacion notificacion = new Notificacion(1L, 1L, "Visita confirmada", "VISITA", LocalDate.now());
        when(notificacionRepository.findByTipoNotificacion("VISITA")).thenReturn(List.of(notificacion));

        List<Notificacion> resultado = notificacionService.findByTipoNotificacion("VISITA");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("VISITA", resultado.get(0).getTipoNotificacion());
    }
}