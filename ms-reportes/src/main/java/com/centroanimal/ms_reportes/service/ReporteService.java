package com.centroanimal.ms_reportes.service;

import com.centroanimal.ms_reportes.model.Reporte;
import com.centroanimal.ms_reportes.repository.ReporteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    public List<Reporte> findAll(){
        return reporteRepository.findAll();
    }

    public Reporte findById(Long id){
        return reporteRepository.findById(id).get();
    }

    public Reporte save(Reporte reporte) {
        if (!List.of("ADOPCIONES", "DONACIONES", "VISITAS", "ANIMALES", "PADRINOS").contains(reporte.getTipoReporte())){
            throw new RuntimeException("Tipo de reporte no valido.");
        }
        reporte.setFechaReporte(LocalDate.now());
        return reporteRepository.save(reporte);
    }

    public void delete(Long id) {
        reporteRepository.deleteById(id);
    }

    public List<Reporte> findByTipoReporte(String tipoReporte) {
        return reporteRepository.findByTipoReporte(tipoReporte);
    }

    public List<Reporte> findByFechaReporte(LocalDate fechaReporte) {
        return reporteRepository.findByFechaReporte(fechaReporte);
    }

}
