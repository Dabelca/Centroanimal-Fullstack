package com.centroanimal.ms_reportes.service;

import com.centroanimal.ms_reportes.model.Reporte;
import com.centroanimal.ms_reportes.repository.ReporteRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;
    private static final Logger log = LoggerFactory.getLogger(ReporteService.class);

    public List<Reporte> findAll(){
        log.info("Listando todos los reportes");
        return reporteRepository.findAll();
    }

    public Reporte findById(Long id){
        log.info("Buscando reporte por id: {}", id);
        return reporteRepository.findById(id).get();
    }

    public Reporte save(Reporte reporte) {
        log.info("Creando nuevo reporte de tipo: {}", reporte.getTipoReporte());
        if (!List.of("ADOPCIONES", "DONACIONES", "VISITAS", "ANIMALES", "PADRINOS").contains(reporte.getTipoReporte())){
            log.warn("Tipo de reporte invalido: {}", reporte.getTipoReporte());
            throw new RuntimeException("Tipo de reporte no valido.");
        }
        reporte.setFechaReporte(LocalDate.now());
        log.info("Reporte creado exitosamente");
        return reporteRepository.save(reporte);
    }

    public void delete(Long id) {
        log.info("Eliminando reporte con id: {}", id);
        reporteRepository.deleteById(id);
        log.info("Reporte {} eliminado exitosamente", id);
    }

    public List<Reporte> findByTipoReporte(String tipoReporte) {
        log.info("Buscando reportes por tipo: {}", tipoReporte);
        return reporteRepository.findByTipoReporte(tipoReporte);
    }

    public List<Reporte> findByFechaReporte(LocalDate fechaReporte) {
        log.info("Buscando reportes por fecha: {}", fechaReporte);
        return reporteRepository.findByFechaReporte(fechaReporte);
    }

}
