package com.centroanimal.ms_adopciones.controller;

import com.centroanimal.ms_adopciones.dto.AdopcionDTO;
import com.centroanimal.ms_adopciones.model.Adopcion;
import com.centroanimal.ms_adopciones.service.AdopcionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/adopciones")
@Tag(name = "Adopciones", description = "Operaciones para la gestión de adopciones del centro")
public class AdopcionController {

    @Autowired
    private AdopcionService adopcionService;

    @GetMapping
    @Operation(summary = "Listar todas las adopciones", description = "Obtiene una lista de todas las adopciones registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de adopciones obtenida exitosamente"),
            @ApiResponse(responseCode = "204", description = "No hay adopciones registradas")
    })
    public ResponseEntity<List<Adopcion>> listar(){
        List<Adopcion> adopciones = adopcionService.findAll();
        if (adopciones.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(adopciones);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar adopción por ID", description = "Retorna una adopción según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Adopción encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Adopción no encontrada")
    })
    public ResponseEntity<Adopcion> buscarPorId(@PathVariable Long id) {
        try {
            Adopcion adopcion = adopcionService.findById(id);
            return ResponseEntity.ok(adopcion);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuario/{idUsuario}")
    @Operation(summary = "Buscar adopción por usuario", description = "Retorna una adopción según su usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Adopción encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Adopción no encontrada")
    })
    public ResponseEntity<List<Adopcion>> buscarPorUsuario(@PathVariable Long idUsuario) {
        List<Adopcion> adopciones = adopcionService.findByIdUsuario(idUsuario);
        if (adopciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(adopciones);
    }

    @GetMapping("/estado/{estado}")
    @Operation(summary = "Buscar adopciones por estado", description = "Retorna adopciones filtradas por estado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente"),
            @ApiResponse(responseCode = "404", description = "No hay adopciones con ese estado")
    })
    public ResponseEntity<List<Adopcion>> buscarPorEstado(@PathVariable String estado) {
        List<Adopcion> adopciones = adopcionService.findByEstado(estado);
        if (adopciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(adopciones);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva adopcion", description = "Registra una nueva adopcion en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Adopcion creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Adopcion> crear(@Valid @RequestBody AdopcionDTO adopcionDTO) {
        Adopcion adopcion = new Adopcion();
        adopcion.setIdUsuario(adopcionDTO.getIdUsuario());
        adopcion.setIdAnimal(adopcionDTO.getIdAnimal());
        return ResponseEntity.status(HttpStatus.CREATED).body(adopcionService.save(adopcion));
    }

    @PutMapping("/{id}/estado")
    @Operation(summary = "Cambiar estado de adopcion", description = "Actualiza el estado de una adopcion existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Adopcion no encontrada")
    })
    public ResponseEntity<Adopcion> cambiarEstado(@PathVariable Long id, @RequestParam String estado) {
        try {
            Adopcion adopcion = adopcionService.cambiarEstado(id, estado);
            return ResponseEntity.ok(adopcion);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una adopcion", description = "Elimina una adopcion del sistema por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Adopcion eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Adopcion no encontrada")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            adopcionService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}












