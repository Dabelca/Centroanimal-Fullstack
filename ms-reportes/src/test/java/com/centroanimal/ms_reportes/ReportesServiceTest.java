package com.centroanimal.ms_reportes;

import com.centroanimal.ms_reportes.model.Reporte;
import com.centroanimal.ms_reportes.repository.ReporteRepository;
import com.centroanimal.ms_reportes.service.ReporteService;
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
class ReporteServiceTest {

    @Autowired
    private ReporteService reporteService;

    @MockBean
    private ReporteRepository reporteRepository;

    @Test
    @DisplayName("Debe retornar lista de reportes")
    void testFindAll() {
        Reporte reporte = new Reporte(1L, "ADOPCIONES", "Reporte mensual", LocalDate.now());
        when(reporteRepository.findAll()).thenReturn(List.of(reporte));

        List<Reporte> resultado = reporteService.findAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    @DisplayName("Debe retornar reporte por ID")
    void testFindById() {
        Reporte reporte = new Reporte(1L, "ADOPCIONES", "Reporte mensual", LocalDate.now());
        when(reporteRepository.findById(1L)).thenReturn(Optional.of(reporte));

        Reporte resultado = reporteService.findById(1L);

        assertNotNull(resultado);
        assertEquals("ADOPCIONES", resultado.getTipoReporte());
    }

    @Test
    @DisplayName("Debe crear reporte correctamente")
    void testSave() {
        Reporte reporte = new Reporte(null, "ADOPCIONES", "Reporte mensual", null);
        Reporte reporteGuardado = new Reporte(1L, "ADOPCIONES", "Reporte mensual", LocalDate.now());
        when(reporteRepository.save(reporte)).thenReturn(reporteGuardado);

        Reporte resultado = reporteService.save(reporte);

        assertNotNull(resultado);
        assertEquals("ADOPCIONES", resultado.getTipoReporte());
    }

    @Test
    @DisplayName("Debe lanzar excepcion si tipo de reporte es invalido")
    void testSaveTipoInvalido() {
        Reporte reporte = new Reporte(null, "INVALIDO", "Reporte mensual", null);

        assertThrows(RuntimeException.class, () -> reporteService.save(reporte));
    }

    @Test
    @DisplayName("Debe eliminar reporte por ID")
    void testDelete() {
        doNothing().when(reporteRepository).deleteById(1L);

        reporteService.delete(1L);

        verify(reporteRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Debe retornar reportes por tipo")
    void testFindByTipoReporte() {
        Reporte reporte = new Reporte(1L, "ADOPCIONES", "Reporte mensual", LocalDate.now());
        when(reporteRepository.findByTipoReporte("ADOPCIONES")).thenReturn(List.of(reporte));

        List<Reporte> resultado = reporteService.findByTipoReporte("ADOPCIONES");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("ADOPCIONES", resultado.get(0).getTipoReporte());
    }

    @Test
    @DisplayName("Debe retornar reportes por fecha")
    void testFindByFechaReporte() {
        LocalDate fecha = LocalDate.now();
        Reporte reporte = new Reporte(1L, "ADOPCIONES", "Reporte mensual", fecha);
        when(reporteRepository.findByFechaReporte(fecha)).thenReturn(List.of(reporte));

        List<Reporte> resultado = reporteService.findByFechaReporte(fecha);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }
}