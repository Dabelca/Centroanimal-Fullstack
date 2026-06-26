package com.centroanimal.ms_voluntariados;

import com.centroanimal.ms_voluntariados.model.Voluntariado;
import com.centroanimal.ms_voluntariados.repository.VoluntariadoRepository;
import com.centroanimal.ms_voluntariados.service.VoluntariadoService;
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
class VoluntariadoServiceTest {

    @Autowired
    private VoluntariadoService voluntariadoService;

    @MockBean
    private VoluntariadoRepository voluntariadoRepository;

    @Test
    @DisplayName("Debe retornar lista de voluntariados")
    void testFindAll() {
        Voluntariado voluntariado = new Voluntariado(1L, 1L, LocalDate.now(), null, "Mañana", "PENDIENTE");
        when(voluntariadoRepository.findAll()).thenReturn(List.of(voluntariado));

        List<Voluntariado> resultado = voluntariadoService.findAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    @DisplayName("Debe retornar voluntariado por ID")
    void testFindById() {
        Voluntariado voluntariado = new Voluntariado(1L, 1L, LocalDate.now(), null, "Mañana", "PENDIENTE");
        when(voluntariadoRepository.findById(1L)).thenReturn(Optional.of(voluntariado));

        Voluntariado resultado = voluntariadoService.findById(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdUsuario());
    }

    @Test
    @DisplayName("Debe crear voluntariado correctamente")
    void testSave() {
        Voluntariado voluntariado = new Voluntariado(null, 1L, null, null, "Mañana", null);
        Voluntariado voluntariadoGuardado = new Voluntariado(1L, 1L, LocalDate.now(), null, "Mañana", "PENDIENTE");

        when(voluntariadoRepository.existsByIdUsuario(1L)).thenReturn(false);
        when(voluntariadoRepository.save(voluntariado)).thenReturn(voluntariadoGuardado);

        Voluntariado resultado = voluntariadoService.save(voluntariado);

        assertNotNull(resultado);
        assertEquals("PENDIENTE", resultado.getEstado());
    }

    @Test
    @DisplayName("Debe lanzar excepcion si usuario ya tiene postulacion")
    void testSaveUsuarioYaPostuló() {
        Voluntariado voluntariado = new Voluntariado(null, 1L, null, null, "Mañana", null);
        when(voluntariadoRepository.existsByIdUsuario(1L)).thenReturn(true);

        assertThrows(RuntimeException.class, () -> voluntariadoService.save(voluntariado));
    }

    @Test
    @DisplayName("Debe eliminar voluntariado por ID")
    void testDelete() {
        doNothing().when(voluntariadoRepository).deleteById(1L);

        voluntariadoService.delete(1L);

        verify(voluntariadoRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Debe cambiar estado de voluntariado")
    void testCambiarEstado() {
        Voluntariado voluntariado = new Voluntariado(1L, 1L, LocalDate.now(), null, "Mañana", "PENDIENTE");
        when(voluntariadoRepository.findById(1L)).thenReturn(Optional.of(voluntariado));
        when(voluntariadoRepository.save(voluntariado)).thenReturn(voluntariado);

        Voluntariado resultado = voluntariadoService.cambiarEstado(1L, "APROBADO");

        assertNotNull(resultado);
        assertEquals("APROBADO", resultado.getEstado());
        assertNotNull(resultado.getFechaInscripcion());
    }

    @Test
    @DisplayName("Debe retornar voluntariados por estado")
    void testFindByEstado() {
        Voluntariado voluntariado = new Voluntariado(1L, 1L, LocalDate.now(), null, "Mañana", "PENDIENTE");
        when(voluntariadoRepository.findByEstado("PENDIENTE")).thenReturn(List.of(voluntariado));

        List<Voluntariado> resultado = voluntariadoService.findByEstado("PENDIENTE");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    @DisplayName("Debe retornar voluntariados por usuario")
    void testFindByIdUsuario() {
        Voluntariado voluntariado = new Voluntariado(1L, 1L, LocalDate.now(), null, "Mañana", "PENDIENTE");
        when(voluntariadoRepository.findByIdUsuario(1L)).thenReturn(List.of(voluntariado));

        List<Voluntariado> resultado = voluntariadoService.findByIdUsuario(1L);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }
}