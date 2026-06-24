package com.centroanimal.ms_visitas.controller;

import com.centroanimal.ms_visitas.dto.VisitaDTO;
import com.centroanimal.ms_visitas.dto.VisitaUpdateDTO;
import com.centroanimal.ms_visitas.model.Visita;
import com.centroanimal.ms_visitas.service.VisitaService;
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
@RequestMapping("/api/visitas")
@Tag(name = "Visitas", description = "Operaciones para la gestión de visitas del centro")
public class VisitaController {

    @Autowired
    private VisitaService visitaService;

    @GetMapping
    @Operation(summary = "Listar todas las vistas", description = "Obtiene una lista de todas las vistas registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de visitas obtenida exitosamente"),
            @ApiResponse(responseCode = "204", description = "No hay visitas registradas")
    })
    public ResponseEntity<List<Visita>> buscarTodas() {
        return ResponseEntity.ok(visitaService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar visitas por ID", description = "Retorna una visitas según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Visita encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Visita no encontrada")
    })
    public ResponseEntity<Visita> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(visitaService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Crear una nueva visita", description = "Registra una nueva visita en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Visita creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Visita> crear(@Valid @RequestBody VisitaDTO visitaDTO) {
        Visita visita = new Visita();
        visita.setIdUsuario(visitaDTO.getIdUsuario());
        visita.setIdAnimal(visitaDTO.getIdAnimal());
        visita.setTipoVisita(visitaDTO.getTipoVisita());
        visita.setFechaVisita(visitaDTO.getFechaVisita());
        return ResponseEntity.status(HttpStatus.CREATED).body(visitaService.crear(visita));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una visita", description = "Actualizar visita existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "visita no encontrada")
    })
    public ResponseEntity<Visita> actualizar(@PathVariable Long id, @Valid @RequestBody VisitaUpdateDTO visitaUpdateDTO) {
        return ResponseEntity.ok(visitaService.actualizar(id, visitaUpdateDTO));
    }

    @PutMapping("/{id}/cancelar")
    @Operation(summary = "Cancelar una visita", description = "Cancelar una visita existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Visita cancelada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Visita no encontrada")
    })
    public ResponseEntity<Visita> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(visitaService.cancelar(id));
    }

    @PutMapping("/{id}/confirmar")
    @Operation(summary = "Confirmar una visita", description = "Confirmar una visita existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Visita confirmada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Visita no encontrada")
    })
    public ResponseEntity<Visita> confirmar(@PathVariable Long id) {
        return ResponseEntity.ok(visitaService.confirmar(id));
    }

    @GetMapping("/usuario/{idUsuario}")
    @Operation(summary = "Buscar visita por usuario", description = "Retorna una visita según su usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Visita encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Visita no encontrada")
    })
    public ResponseEntity<List<Visita>> buscarPorUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(visitaService.findByIdUsuario(idUsuario));
    }

    @GetMapping("/animal/{idAnimal}")
    @Operation(summary = "Buscar visita por animal", description = "Retorna una visita según animal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Visita encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Visita no encontrada")
    })
    public ResponseEntity<List<Visita>> buscarPorAnimal(@PathVariable Long idAnimal) {
        return ResponseEntity.ok(visitaService.findByIdAnimal(idAnimal));
    }

    @GetMapping("/estado/{estado}")
    @Operation(summary = "Buscar visita por estado", description = "Retorna una visita según estado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Visita encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Visita no encontrada")
    })
    public ResponseEntity<List<Visita>> buscarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(visitaService.findByEstado(estado));
    }

    @GetMapping("/fecha/{fechaVisita}")
    @Operation(summary = "Buscar visita por fecha", description = "Retorna una visita según fecha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Visita encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Visita no encontrada")
    })
    public ResponseEntity<List<Visita>> buscarPorFecha(@PathVariable LocalDate fechaVisita) {
        return ResponseEntity.ok(visitaService.findByFechaVisita(fechaVisita));
    }

    @GetMapping("/tipo/{tipoVisita}")
    @Operation(summary = "Buscar visita por tipo", description = "Retorna una visita según tipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Visita encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Visita no encontrada")
    })
    public ResponseEntity<List<Visita>> buscarPorTipo(@PathVariable String tipoVisita) {
        return ResponseEntity.ok(visitaService.findByTipoVisita(tipoVisita));
    }
}