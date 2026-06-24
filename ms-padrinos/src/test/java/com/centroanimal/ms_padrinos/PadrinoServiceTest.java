package com.centroanimal.ms_padrinos;

import com.centroanimal.ms_padrinos.client.AnimalClientService;
import com.centroanimal.ms_padrinos.model.Padrino;
import com.centroanimal.ms_padrinos.repository.PadrinoRepository;
import com.centroanimal.ms_padrinos.service.PadrinoService;
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
class PadrinoServiceTest {

    @Autowired
    private PadrinoService padrinoService;

    @MockBean
    private PadrinoRepository padrinoRepository;

    @MockBean
    private AnimalClientService animalClientService;

    @Test
    @DisplayName("Debe retornar lista de padrinos")
    void testFindAll() {
        Padrino padrino = new Padrino(1L, 1L, 1L, 50000.0, LocalDate.now(), "ACTIVO");
        when(padrinoRepository.findAll()).thenReturn(List.of(padrino));

        List<Padrino> resultado = padrinoService.findAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("ACTIVO", resultado.get(0).getEstado());
    }

    @Test
    @DisplayName("Debe retornar padrino por ID")
    void testFindById() {
        Padrino padrino = new Padrino(1L, 1L, 1L, 50000.0, LocalDate.now(), "ACTIVO");
        when(padrinoRepository.findById(1L)).thenReturn(Optional.of(padrino));

        Padrino resultado = padrinoService.findById(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdUsuario());
        assertEquals(1L, resultado.getIdAnimal());
    }

    @Test
    @DisplayName("Debe crear padrino correctamente")
    void testSave() {
        Padrino padrino = new Padrino(null, 1L, 1L, 50000.0, null, null);
        Padrino padrinoGuardado = new Padrino(1L, 1L, 1L, 50000.0, LocalDate.now(), "ACTIVO");

        doNothing().when(animalClientService).verificarAnimalExiste(1L);
        when(padrinoRepository.existsByIdUsuarioAndIdAnimal(1L, 1L)).thenReturn(false);
        when(padrinoRepository.save(padrino)).thenReturn(padrinoGuardado);

        Padrino resultado = padrinoService.save(padrino);

        assertNotNull(resultado);
        assertEquals("ACTIVO", resultado.getEstado());
    }

    @Test
    @DisplayName("Debe lanzar excepcion si usuario ya es padrino del animal")
    void testSavePadrinoYaExiste() {
        Padrino padrino = new Padrino(null, 1L, 1L, 50000.0, null, null);

        doNothing().when(animalClientService).verificarAnimalExiste(1L);
        when(padrinoRepository.existsByIdUsuarioAndIdAnimal(1L, 1L)).thenReturn(true);

        assertThrows(RuntimeException.class, () -> padrinoService.save(padrino));
    }

    @Test
    @DisplayName("Debe eliminar padrino por ID")
    void testDelete() {
        doNothing().when(padrinoRepository).deleteById(1L);

        padrinoService.delete(1L);

        verify(padrinoRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Debe cambiar estado de padrino")
    void testCambiarEstado() {
        Padrino padrino = new Padrino(1L, 1L, 1L, 50000.0, LocalDate.now(), "ACTIVO");
        when(padrinoRepository.findById(1L)).thenReturn(Optional.of(padrino));
        when(padrinoRepository.save(padrino)).thenReturn(padrino);

        Padrino resultado = padrinoService.cambiarEstado(1L, "INACTIVO");

        assertNotNull(resultado);
        assertEquals("INACTIVO", resultado.getEstado());
    }

    @Test
    @DisplayName("Debe actualizar cuota de padrino")
    void testActualizarCuota() {
        Padrino padrino = new Padrino(1L, 1L, 1L, 50000.0, LocalDate.now(), "ACTIVO");
        when(padrinoRepository.findById(1L)).thenReturn(Optional.of(padrino));
        when(padrinoRepository.save(padrino)).thenReturn(padrino);

        Padrino resultado = padrinoService.actualizarCuota(1L, 75000.0);

        assertNotNull(resultado);
        assertEquals(75000.0, resultado.getMontoCuota());
    }

    @Test
    @DisplayName("Debe retornar padrinos por estado")
    void testFindByEstado() {
        Padrino padrino = new Padrino(1L, 1L, 1L, 50000.0, LocalDate.now(), "ACTIVO");
        when(padrinoRepository.findByEstado("ACTIVO")).thenReturn(List.of(padrino));

        List<Padrino> resultado = padrinoService.findByEstado("ACTIVO");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("ACTIVO", resultado.get(0).getEstado());
    }
}