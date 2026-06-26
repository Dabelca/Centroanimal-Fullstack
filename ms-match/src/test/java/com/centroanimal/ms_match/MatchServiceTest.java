package com.centroanimal.ms_match;

import com.centroanimal.ms_match.model.Matching;
import com.centroanimal.ms_match.repository.MatchingRepository;
import com.centroanimal.ms_match.service.MatchingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class MatchingServiceTest {

    @Autowired
    private MatchingService matchingService;

    @MockBean
    private MatchingRepository matchingRepository;

    @Test
    @DisplayName("Debe retornar lista de matchings")
    void testFindAll() {
        Matching matching = new Matching(1L, 1L, "Perro", "Pequeño", 2, "PENDIENTE");
        when(matchingRepository.findAll()).thenReturn(List.of(matching));

        List<Matching> resultado = matchingService.findAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    @DisplayName("Debe retornar matching por ID")
    void testFindById() {
        Matching matching = new Matching(1L, 1L, "Perro", "Pequeño", 2, "PENDIENTE");
        when(matchingRepository.findById(1L)).thenReturn(Optional.of(matching));

        Matching resultado = matchingService.findById(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdUsuario());
    }

    @Test
    @DisplayName("Debe lanzar excepcion si matching no existe")
    void testFindByIdNoExiste() {
        when(matchingRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> matchingService.findById(99L));
    }

    @Test
    @DisplayName("Debe crear matching correctamente")
    void testCrear() {
        Matching matching = new Matching(null, 1L, "Perro", "Pequeño", 2, null);
        Matching matchingGuardado = new Matching(1L, 1L, "Perro", "Pequeño", 2, "PENDIENTE");
        when(matchingRepository.save(matching)).thenReturn(matchingGuardado);

        Matching resultado = matchingService.crear(matching);

        assertNotNull(resultado);
        assertEquals("PENDIENTE", resultado.getResultado());
    }

    @Test
    @DisplayName("Debe lanzar excepcion si idUsuario es nulo")
    void testCrearSinUsuario() {
        Matching matching = new Matching(null, null, "Perro", "Pequeño", 2, null);

        assertThrows(RuntimeException.class, () -> matchingService.crear(matching));
    }

    @Test
    @DisplayName("Debe eliminar matching por ID")
    void testEliminar() {
        Matching matching = new Matching(1L, 1L, "Perro", "Pequeño", 2, "PENDIENTE");
        when(matchingRepository.findById(1L)).thenReturn(Optional.of(matching));
        doNothing().when(matchingRepository).deleteById(1L);

        matchingService.eliminar(1L);

        verify(matchingRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Debe retornar matchings por usuario")
    void testFindByIdUsuario() {
        Matching matching = new Matching(1L, 1L, "Perro", "Pequeño", 2, "PENDIENTE");
        when(matchingRepository.findByIdUsuario(1L)).thenReturn(List.of(matching));

        List<Matching> resultado = matchingService.findByIdUsuario(1L);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    @DisplayName("Debe retornar matchings por especie")
    void testFindByEspeciePreferida() {
        Matching matching = new Matching(1L, 1L, "Perro", "Pequeño", 2, "PENDIENTE");
        when(matchingRepository.findByEspeciePreferida("Perro")).thenReturn(List.of(matching));

        List<Matching> resultado = matchingService.findByEspeciePreferida("Perro");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Perro", resultado.get(0).getEspeciePreferida());
    }
}