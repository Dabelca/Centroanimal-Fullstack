package com.centroanimal.ms_visitas;

import com.centroanimal.ms_visitas.client.AnimalClientService;
import com.centroanimal.ms_visitas.dto.VisitaUpdateDTO;
import com.centroanimal.ms_visitas.model.Visita;
import com.centroanimal.ms_visitas.repository.VisitaRepository;
import com.centroanimal.ms_visitas.service.VisitaService;
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
class VisitaServiceTest {

    @Autowired
    private VisitaService visitaService;

    @MockBean
    private VisitaRepository visitaRepository;

    @MockBean
    private AnimalClientService animalClientService;

    @Test
    @DisplayName("Debe retornar lista de visitas")
    void testFindAll() {
        Visita visita = new Visita(1L, 1L, null, "VISITA_CENTRO", LocalDate.now().plusDays(1), "PENDIENTE");
        when(visitaRepository.findAll()).thenReturn(List.of(visita));

        List<Visita> resultado = visitaService.findAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    @DisplayName("Debe retornar visita por ID")
    void testFindById() {
        Visita visita = new Visita(1L, 1L, null, "VISITA_CENTRO", LocalDate.now().plusDays(1), "PENDIENTE");
        when(visitaRepository.findById(1L)).thenReturn(Optional.of(visita));

        Visita resultado = visitaService.findById(1L);

        assertNotNull(resultado);
        assertEquals("VISITA_CENTRO", resultado.getTipoVisita());
    }

    @Test
    @DisplayName("Debe crear visita de tipo VISITA_CENTRO correctamente")
    void testCrearVisitaCentro() {
        Visita visita = new Visita(null, 1L, null, "VISITA_CENTRO", LocalDate.now().plusDays(1), null);
        Visita visitaGuardada = new Visita(1L, 1L, null, "VISITA_CENTRO", LocalDate.now().plusDays(1), "PENDIENTE");

        when(visitaRepository.existsByIdUsuarioAndFechaVisitaAndEstadoIn(any(), any(), any())).thenReturn(false);
        when(visitaRepository.save(visita)).thenReturn(visitaGuardada);

        Visita resultado = visitaService.crear(visita);

        assertNotNull(resultado);
        assertEquals("PENDIENTE", resultado.getEstado());
    }

    @Test
    @DisplayName("Debe lanzar excepcion si fecha de visita es pasada")
    void testCrearVisitaFechaInvalida() {
        Visita visita = new Visita(null, 1L, null, "VISITA_CENTRO", LocalDate.now().minusDays(1), null);

        assertThrows(RuntimeException.class, () -> visitaService.crear(visita));
    }

    @Test
    @DisplayName("Debe lanzar excepcion si usuario ya tiene visita en esa fecha")
    void testCrearVisitaUsuarioDuplicado() {
        Visita visita = new Visita(null, 1L, null, "VISITA_CENTRO", LocalDate.now().plusDays(1), null);

        when(visitaRepository.existsByIdUsuarioAndFechaVisitaAndEstadoIn(any(), any(), any())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> visitaService.crear(visita));
    }

    @Test
    @DisplayName("Debe cancelar visita correctamente")
    void testCancelar() {
        Visita visita = new Visita(1L, 1L, null, "VISITA_CENTRO", LocalDate.now().plusDays(1), "PENDIENTE");
        when(visitaRepository.findById(1L)).thenReturn(Optional.of(visita));
        when(visitaRepository.save(visita)).thenReturn(visita);

        Visita resultado = visitaService.cancelar(1L);

        assertNotNull(resultado);
        assertEquals("CANCELADA", resultado.getEstado());
    }

    @Test
    @DisplayName("Debe confirmar visita correctamente")
    void testConfirmar() {
        Visita visita = new Visita(1L, 1L, null, "VISITA_CENTRO", LocalDate.now().plusDays(1), "PENDIENTE");
        when(visitaRepository.findById(1L)).thenReturn(Optional.of(visita));
        when(visitaRepository.save(visita)).thenReturn(visita);

        Visita resultado = visitaService.confirmar(1L);

        assertNotNull(resultado);
        assertEquals("CONFIRMADA", resultado.getEstado());
    }

    @Test
    @DisplayName("Debe lanzar excepcion al cancelar visita ya cancelada")
    void testCancelarVisitaYaCancelada() {
        Visita visita = new Visita(1L, 1L, null, "VISITA_CENTRO", LocalDate.now().plusDays(1), "CANCELADA");
        when(visitaRepository.findById(1L)).thenReturn(Optional.of(visita));

        assertThrows(RuntimeException.class, () -> visitaService.cancelar(1L));
    }

    @Test
    @DisplayName("Debe retornar visitas por estado")
    void testFindByEstado() {
        Visita visita = new Visita(1L, 1L, null, "VISITA_CENTRO", LocalDate.now().plusDays(1), "PENDIENTE");
        when(visitaRepository.findByEstado("PENDIENTE")).thenReturn(List.of(visita));

        List<Visita> resultado = visitaService.findByEstado("PENDIENTE");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }
}
