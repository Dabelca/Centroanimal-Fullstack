package com.centroanimal.ms_reportes.controller;

import com.centroanimal.ms_reportes.model.Reporte;
import com.centroanimal.ms_reportes.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public ResponseEntity<List<Reporte>> listar(){
        List<Reporte> reportes = reporteService.findAll();
        if (reportes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reportes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reporte> buscarPorId(@PathVariable Long id){
        try {
            Reporte reporte = reporteService.findById(id);
            return ResponseEntity.ok(reporte);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/fechaReporte/{fechaReporte}")
    public ResponseEntity<List<Reporte>> buscarPorFechaReporte(@PathVariable LocalDate fechaReporte){
            List<Reporte> reportes = reporteService.findByFechaReporte(fechaReporte);
            if(reportes.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(reportes);
    }


    @GetMapping("/tipoReporte/{tipoReporte}")
    public ResponseEntity<List<Reporte>> buscarPorTipoReporte(@PathVariable String tipoReporte){
        List<Reporte> reportes = reporteService.findByTipoReporte(tipoReporte);
        if (reportes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reportes);
    }

    @PostMapping
    public ResponseEntity<Reporte> crear(@RequestBody Reporte reporte){
        try {
            Reporte nuevo = reporteService.save(reporte);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try {
            reporteService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}

