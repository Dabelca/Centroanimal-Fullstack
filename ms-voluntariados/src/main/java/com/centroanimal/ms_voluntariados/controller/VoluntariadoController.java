package com.centroanimal.ms_voluntariados.controller;


import com.centroanimal.ms_voluntariados.dto.VoluntariadoDTO;
import com.centroanimal.ms_voluntariados.model.Voluntariado;
import com.centroanimal.ms_voluntariados.service.VoluntariadoService;
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
@RequestMapping("/api/v1/voluntariados")
@Tag(name = "Voluntariados", description = "Operaciones para la gestión de voluntariados del centro")
public class VoluntariadoController {

    @Autowired
    private VoluntariadoService voluntariadoService;


    @GetMapping
    @Operation(summary = "Listar todos los voluntariados", description = "Obtiene una lista de todos los voluntariados registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de voluntariados obtenida exitosamente"),
            @ApiResponse(responseCode = "204", description = "No hay voluntariados registradas")
    })
    public ResponseEntity<List<Voluntariado>> listar() {
        List<Voluntariado> voluntariados = voluntariadoService.findAll();
        if (voluntariados.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.ok(voluntariados);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Buscar voluntariado por ID", description = "Retorna un voluntariado según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "voluntariado encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "voluntariado no encontrado")
    })
    public ResponseEntity<Voluntariado> buscarPoId(@PathVariable Long id) {
        try {
            Voluntariado voluntariado = voluntariadoService.findById(id);
            return ResponseEntity.ok(voluntariado);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuario/{idUsuario}")
    @Operation(summary = "Buscar voluntariado por usuario", description = "Retorna un voluntariado según usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Voluntariado encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Voluntariado no encontrado")
    })
    public ResponseEntity<List<Voluntariado>> buscarPorIdUsuario(@PathVariable Long idUsuario) {
        List<Voluntariado> voluntariados = voluntariadoService.findByIdUsuario(idUsuario);
        if (voluntariados.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(voluntariados);
    }

    @GetMapping("/estado/{estado}")
    @Operation(summary = "Buscar voluntariado por estado", description = "Retorna un voluntariado según estado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Voluntariado encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Voluntariado no encontrado")
    })
    public ResponseEntity<List<Voluntariado>> buscarPorEstado(@PathVariable String estado) {
        List<Voluntariado> voluntariados = voluntariadoService.findByEstado(estado);
        if (voluntariados.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(voluntariados);
    }

    @GetMapping("/fechaPostulacion/{fechaPostulacion}")
    @Operation(summary = "Buscar voluntariado por fecha de postulación", description = "Retorna un voluntariado según fecha de postulación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Voluntariado encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Voluntariado no encontrado")
    })
    public ResponseEntity<List<Voluntariado>> buscarPorFechaPostulacion(@PathVariable LocalDate fechaPostulacion){
        List<Voluntariado> voluntariados = voluntariadoService.findByFechaPostulacion(fechaPostulacion);
        if (voluntariados.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(voluntariados);
    }

    @GetMapping("/fechaInscripcion/{fechaIncripcion}")
    @Operation(summary = "Buscar voluntariado por fecha inscripción", description = "Retorna un voluntariado según fecha inscripción")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Voluntariado encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Voluntariado no encontrado")
    })
    public ResponseEntity<List<Voluntariado>> buscarPorFechaInscripcion(@PathVariable LocalDate fechaInscripcion){
        List<Voluntariado> voluntariados = voluntariadoService.findByFechaInscripcion(fechaInscripcion);
        if (voluntariados.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(voluntariados);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo voluntariado", description = "Registra un nuevo voluntariado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Voluntariado creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Voluntariado> crear(@Valid @RequestBody VoluntariadoDTO voluntariadoDTO) {
        try {
            Voluntariado voluntariado = new Voluntariado();
            voluntariado.setIdUsuario(voluntariadoDTO.getIdUsuario());
            voluntariado.setHorario(voluntariadoDTO.getHorario());
            return ResponseEntity.status(HttpStatus.CREATED).body(voluntariadoService.save(voluntariado));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/estado")
    @Operation(summary = "Cambiar estado de voluntariado", description = "Actualiza el estado de un voluntariado existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Voluntariado> cambiarEstado(@PathVariable Long id, @RequestParam String estado) {
        try {
            Voluntariado voluntariado = voluntariadoService.cambiarEstado(id, estado);
            return ResponseEntity.ok(voluntariado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar voluntariado", description = "Elimina voluntariado del sistema por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "voluntariado eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "voluntariado no encontrado")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            voluntariadoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();

        }
    }




}




