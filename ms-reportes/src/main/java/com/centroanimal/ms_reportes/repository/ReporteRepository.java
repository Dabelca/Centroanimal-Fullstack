package com.centroanimal.ms_reportes.repository;

import com.centroanimal.ms_reportes.model.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long> {


    List<Reporte> findByTipoReporte(String tipoReporte);

    List<Reporte> findByFechaReporte(LocalDate fechaReporte);

    }


