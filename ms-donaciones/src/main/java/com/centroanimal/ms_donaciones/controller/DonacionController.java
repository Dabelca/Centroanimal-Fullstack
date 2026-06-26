package com.centroanimal.ms_donaciones.controller;

import com.centroanimal.ms_donaciones.dto.DonacionDTO;
import com.centroanimal.ms_donaciones.model.Donacion;
import com.centroanimal.ms_donaciones.service.DonacionService;
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
@RequestMapping("/api/v1/donaciones")
@Tag(name = "Donaciones", description = "Operaciones para la gestión de donaciones del centro")
public class DonacionController {

    @Autowired
    private DonacionService donacionService;

    @GetMapping
    @Operation(summary = "Listar todas las donaciones", description = "Obtiene una lista de todas las donaciones registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de donaciones obtenida exitosamente"),
            @ApiResponse(responseCode = "204", description = "No hay donaciones registradas")
    })
    public ResponseEntity<List<Donacion>> listar() {
        List<Donacion> donaciones = donacionService.findAll();
        if (donaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(donaciones);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar donación por ID", description = "Retorna una donación según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Donación encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Donación no encontrada")
    })
    public ResponseEntity<Donacion> buscarPorId(@PathVariable Long id) {
        try {
            Donacion donacion = donacionService.findById(id);
            return ResponseEntity.ok(donacion);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuario/{idUsuario}")
    @Operation(summary = "Buscar donación por usuario", description = "Retorna una donación según su usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Donación encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Donación no encontrada")
    })
    public ResponseEntity<List<Donacion>> buscarPorUsuario(@PathVariable Long idUsuario) {
        List<Donacion> donaciones = donacionService.findByIdUsuario(idUsuario);
        if (donaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(donaciones);
    }

    @GetMapping("/monto/{monto}")
    @Operation(summary = "Buscar donación por monto", description = "Retorna una donación según su monto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Donación encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Donación no encontrada")
    })
    public ResponseEntity<List<Donacion>> buscarPorMonto(@PathVariable Double monto) {
        List<Donacion> donaciones = donacionService.findByMontoGreaterThanEqual(monto);
        if (donaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(donaciones);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva donación", description = "Registra una nueva donación en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Donación creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Donacion> crear(@Valid @RequestBody DonacionDTO donacionDTO) {
        try {
            Donacion donacion = new Donacion();
            donacion.setIdUsuario(donacionDTO.getIdUsuario());
            donacion.setMonto(donacionDTO.getMonto());
            donacion.setMensaje(donacionDTO.getMensaje());
            return ResponseEntity.status(HttpStatus.CREATED).body(donacionService.save(donacion));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
