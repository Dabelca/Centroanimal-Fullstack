package com.centroanimal.ms_padrinos.controller;

import com.centroanimal.ms_padrinos.dto.PadrinoDTO;
import com.centroanimal.ms_padrinos.model.Padrino;
import com.centroanimal.ms_padrinos.service.PadrinoService;
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
@RequestMapping("/api/v1/padrinos")
@Tag(name = "Padrinos", description = "Operaciones para la gestión de padrinos del centro")
public class PadrinoController {

    @Autowired
    private PadrinoService padrinoService;

    @GetMapping
    @Operation(summary = "Listar todos los padrinos", description = "Obtiene una lista de todos los padrinos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de padrinos obtenida exitosamente"),
            @ApiResponse(responseCode = "204", description = "No hay padrinos registrados")
    })
    public ResponseEntity<List<Padrino>> listar() {
        List<Padrino> padrinos = padrinoService.findAll();
        if (padrinos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(padrinos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar padrino por ID", description = "Retorna un padrino según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "padrino encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "padrino no encontrado")
    })
    public ResponseEntity<Padrino> buscarPorId(@PathVariable Long id) {
        try {
            Padrino padrino = padrinoService.findById(id);
            return ResponseEntity.ok(padrino);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuario/{idUsuario}")
    @Operation(summary = "Buscar padrino por usuario", description = "Retorna un padrino según su usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "padrino encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "padrino no encontrado")
    })
    public ResponseEntity<List<Padrino>> buscarPorUsuario(@PathVariable Long idUsuario) {
        List<Padrino> padrinos = padrinoService.findByIdUsuario(idUsuario);
        if (padrinos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(padrinos);
    }

    @GetMapping("/animal/{idAnimal}")
    @Operation(summary = "Buscar padrino por animal", description = "Retorna un padrino según su animal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "padrino encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "padrino no encontrado")
    })
    public ResponseEntity<List<Padrino>> buscarPorAnimal(@PathVariable Long idAnimal) {
        List<Padrino> padrinos = padrinoService.findByIdAnimal(idAnimal);
        if (padrinos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(padrinos);
    }

    @GetMapping("/estado/{estado}")
    @Operation(summary = "Buscar padrino por estado", description = "Retorna un padrino según su estado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "padrino encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "padrino no encontrado")
    })
    public ResponseEntity<List<Padrino>> buscarPorEstado(@PathVariable String estado) {
        List<Padrino> padrinos = padrinoService.findByEstado(estado);
        if (padrinos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(padrinos);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo padrino", description = "Registra un nuevo padrino en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "nuevo padrino creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Padrino> crear(@Valid @RequestBody PadrinoDTO padrinoDTO) {

            Padrino padrino = new Padrino();
            padrino.setIdUsuario(padrinoDTO.getIdUsuario());
            padrino.setIdAnimal(padrinoDTO.getIdAnimal());
            padrino.setMontoCuota(padrinoDTO.getMontoCuota());
            return ResponseEntity.status(HttpStatus.CREATED).body(padrinoService.save(padrino));
    }

    @PutMapping("/{id}/estado")
    @Operation(summary = "Cambiar estado de padrino", description = "Actualiza el estado de un padrino existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "padrino creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Padrino> cambiarEstado(@PathVariable Long id, @RequestParam String estado) {
        try {
            Padrino padrino = padrinoService.cambiarEstado(id, estado);
            return ResponseEntity.ok(padrino);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/cuota")
    @Operation(summary = "Actualizar cuota de padrino", description = "Actuliza la cuota del padrino")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuota actualizada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos")
    })
    public ResponseEntity<Padrino> actualizarCuota(@PathVariable Long id, @RequestParam Double montoCuota) {
        try {
            Padrino padrino = padrinoService.actualizarCuota(id, montoCuota);
            return ResponseEntity.ok(padrino);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar padrino", description = "Elimina padrino del sistema por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "padrino eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "padrino no encontrada")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            padrinoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}






