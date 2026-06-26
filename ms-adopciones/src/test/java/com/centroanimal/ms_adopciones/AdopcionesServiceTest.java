package com.centroanimal.ms_adopciones;

import com.centroanimal.ms_adopciones.client.AnimalClientService;
import com.centroanimal.ms_adopciones.model.Adopcion;
import com.centroanimal.ms_adopciones.repository.AdopcionRepository;
import com.centroanimal.ms_adopciones.service.AdopcionService;
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
class AdopcionServiceTest {

    @Autowired
    private AdopcionService adopcionService;

    @MockBean
    private AdopcionRepository adopcionRepository;

    @MockBean
    private AnimalClientService animalClientService;

    @Test
    @DisplayName("Debe retornar lista de adopciones")
    void testFindAll() {
        Adopcion adopcion = new Adopcion(1L, 1L, 1L, LocalDate.now(), "PENDIENTE");
        when(adopcionRepository.findAll()).thenReturn(List.of(adopcion));

        List<Adopcion> resultado = adopcionService.findAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("PENDIENTE", resultado.get(0).getEstado());
    }

    @Test
    @DisplayName("Debe retornar adopcion por ID")
    void testFindById() {
        Adopcion adopcion = new Adopcion(1L, 1L, 1L, LocalDate.now(), "PENDIENTE");
        when(adopcionRepository.findById(1L)).thenReturn(Optional.of(adopcion));

        Adopcion resultado = adopcionService.findById(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdUsuario());
        assertEquals(1L, resultado.getIdAnimal());
    }

    @Test
    @DisplayName("Debe crear adopcion correctamente")
    void testSave() {
        Adopcion adopcion = new Adopcion(null, 1L, 1L, null, null);
        Adopcion adopcionGuardada = new Adopcion(1L, 1L, 1L, LocalDate.now(), "PENDIENTE");

        doNothing().when(animalClientService).verificarAnimalExiste(1L);
        when(adopcionRepository.existsByIdAnimal(1L)).thenReturn(false);
        when(adopcionRepository.save(adopcion)).thenReturn(adopcionGuardada);

        Adopcion resultado = adopcionService.save(adopcion);

        assertNotNull(resultado);
        assertEquals("PENDIENTE", resultado.getEstado());
    }

    @Test
    @DisplayName("Debe lanzar excepcion si animal ya fue adoptado")
    void testSaveAnimalYaAdoptado() {
        Adopcion adopcion = new Adopcion(null, 1L, 1L, null, null);

        doNothing().when(animalClientService).verificarAnimalExiste(1L);
        when(adopcionRepository.existsByIdAnimal(1L)).thenReturn(true);

        assertThrows(RuntimeException.class, () -> adopcionService.save(adopcion));
    }

    @Test
    @DisplayName("Debe eliminar adopcion por ID")
    void testDelete() {
        doNothing().when(adopcionRepository).deleteById(1L);

        adopcionService.delete(1L);

        verify(adopcionRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Debe retornar adopciones por estado")
    void testFindByEstado() {
        Adopcion adopcion = new Adopcion(1L, 1L, 1L, LocalDate.now(), "PENDIENTE");
        when(adopcionRepository.findByEstado("PENDIENTE")).thenReturn(List.of(adopcion));

        List<Adopcion> resultado = adopcionService.findByEstado("PENDIENTE");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("PENDIENTE", resultado.get(0).getEstado());
    }

    @Test
    @DisplayName("Debe cambiar estado de adopcion")
    void testCambiarEstado() {
        Adopcion adopcion = new Adopcion(1L, 1L, 1L, LocalDate.now(), "PENDIENTE");
        when(adopcionRepository.findById(1L)).thenReturn(Optional.of(adopcion));
        when(adopcionRepository.save(adopcion)).thenReturn(adopcion);

        Adopcion resultado = adopcionService.cambiarEstado(1L, "APROBADA");

        assertNotNull(resultado);
        assertEquals("APROBADA", resultado.getEstado());
    }
}