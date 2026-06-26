package com.centroanimal.ms_reportes.controller;

import com.centroanimal.ms_reportes.dto.ReporteDTO;
import com.centroanimal.ms_reportes.model.Reporte;
import com.centroanimal.ms_reportes.service.ReporteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/reportes")
@Tag(name = "Reportes", description = "Operaciones para la gestión de reportes del centro")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    @Operation(summary = "Listar todos los reportes", description = "Obtiene una lista de todos los reportes registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de reportes obtenida exitosamente"),
            @ApiResponse(responseCode = "204", description = "No hay reportes registrados")
    })
    public ResponseEntity<List<Reporte>> listar(){
        List<Reporte> reportes = reporteService.findAll();
        if (reportes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reportes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar reporte por ID", description = "Retorna un reporte según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reporte encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Reporte no encontrado")
    })
    public ResponseEntity<Reporte> buscarPorId(@PathVariable Long id){
        try {
            Reporte reporte = reporteService.findById(id);
            return ResponseEntity.ok(reporte);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/fechaReporte/{fechaReporte}")
    @Operation(summary = "Buscar reporte por fecha", description = "Retorna un reporte según fecha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reporte encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Reporte no encontrado")
    })
    public ResponseEntity<List<Reporte>> buscarPorFechaReporte(@PathVariable LocalDate fechaReporte){
            List<Reporte> reportes = reporteService.findByFechaReporte(fechaReporte);
            if(reportes.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(reportes);
    }


    @GetMapping("/tipoReporte/{tipoReporte}")
    @Operation(summary = "Buscar reporte según tipo", description = "Retorna un reporte según su tipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reporte encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Reporte no encontrado")
    })
    public ResponseEntity<List<Reporte>> buscarPorTipoReporte(@PathVariable String tipoReporte){
        List<Reporte> reportes = reporteService.findByTipoReporte(tipoReporte);
        if (reportes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reportes);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo reporte", description = "Registra un nuevo reporte en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reporte creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Reporte> crear(@Valid @RequestBody ReporteDTO reporteDTO){
        try {
            Reporte reporte = new Reporte();
            reporte.setTipoReporte(reporteDTO.getTipoReporte());
            reporte.setDescripcion(reporteDTO.getDescripcion());
            Reporte nuevo = reporteService.save(reporte);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar reporte", description = "Elimina un reporte del sistema por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Reporte eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Reporte no encontrado")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try {
            reporteService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}

