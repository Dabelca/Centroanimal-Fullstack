package com.centroanimal.ms_donaciones;

import com.centroanimal.ms_donaciones.model.Donacion;
import com.centroanimal.ms_donaciones.repository.DonacionRepository;
import com.centroanimal.ms_donaciones.service.DonacionService;
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
class DonacionServiceTest {

    @Autowired
    private DonacionService donacionService;

    @MockBean
    private DonacionRepository donacionRepository;

    @Test
    @DisplayName("Debe retornar lista de donaciones")
    void testFindAll() {
        Donacion donacion = new Donacion(1L, 1L, 50000.0, LocalDate.now(), "Gracias");
        when(donacionRepository.findAll()).thenReturn(List.of(donacion));

        List<Donacion> resultado = donacionService.findAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    @DisplayName("Debe retornar donacion por ID")
    void testFindById() {
        Donacion donacion = new Donacion(1L, 1L, 50000.0, LocalDate.now(), "Gracias");
        when(donacionRepository.findById(1L)).thenReturn(Optional.of(donacion));

        Donacion resultado = donacionService.findById(1L);

        assertNotNull(resultado);
        assertEquals(50000.0, resultado.getMonto());
    }

    @Test
    @DisplayName("Debe crear donacion correctamente")
    void testSave() {
        Donacion donacion = new Donacion(null, 1L, 50000.0, null, "Gracias");
        Donacion donacionGuardada = new Donacion(1L, 1L, 50000.0, LocalDate.now(), "Gracias");
        when(donacionRepository.save(donacion)).thenReturn(donacionGuardada);

        Donacion resultado = donacionService.save(donacion);

        assertNotNull(resultado);
        assertEquals(50000.0, resultado.getMonto());
    }

    @Test
    @DisplayName("Debe lanzar excepcion si monto es menor o igual a 0")
    void testSaveMontoInvalido() {
        Donacion donacion = new Donacion(null, 1L, 0.0, null, "Gracias");

        assertThrows(RuntimeException.class, () -> donacionService.save(donacion));
    }

    @Test
    @DisplayName("Debe crear donacion anonima correctamente")
    void testSaveAnonima() {
        Donacion donacion = new Donacion(null, null, 50000.0, null, "Gracias");
        Donacion donacionGuardada = new Donacion(1L, null, 50000.0, LocalDate.now(), "Donación anónima - Gracias");
        when(donacionRepository.save(donacion)).thenReturn(donacionGuardada);

        Donacion resultado = donacionService.save(donacion);

        assertNotNull(resultado);
        assertTrue(resultado.getMensaje().contains("anónima"));
    }

    @Test
    @DisplayName("Debe retornar donaciones por usuario")
    void testFindByIdUsuario() {
        Donacion donacion = new Donacion(1L, 1L, 50000.0, LocalDate.now(), "Gracias");
        when(donacionRepository.findByIdUsuario(1L)).thenReturn(List.of(donacion));

        List<Donacion> resultado = donacionService.findByIdUsuario(1L);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    @DisplayName("Debe retornar donaciones con monto mayor o igual")
    void testFindByMontoGreaterThanEqual() {
        Donacion donacion = new Donacion(1L, 1L, 50000.0, LocalDate.now(), "Gracias");
        when(donacionRepository.findByMontoGreaterThanEqual(30000.0)).thenReturn(List.of(donacion));

        List<Donacion> resultado = donacionService.findByMontoGreaterThanEqual(30000.0);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }
}