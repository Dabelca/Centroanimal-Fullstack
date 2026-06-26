package com.centroanimal.ms_notificaciones.controller;

import com.centroanimal.ms_notificaciones.dto.NotificacionDTO;
import com.centroanimal.ms_notificaciones.model.Notificacion;
import com.centroanimal.ms_notificaciones.service.NotificacionService;
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
@RequestMapping("/api/notificaciones")
@Tag(name = "Notificaciones", description = "Operaciones para la gestión de notificaciones del centro")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping
    @Operation(summary = "Listar todas las notificaciones", description = "Obtiene una lista de todas las notificaciones registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de notificaciones obtenida exitosamente"),
            @ApiResponse(responseCode = "204", description = "No hay notificaciones registradas")
    })
    public ResponseEntity<List<Notificacion>> buscarTodas() {
        return ResponseEntity.ok(notificacionService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar notificación por ID", description = "Retorna una notificación según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "notificación encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "notificación no encontrada")
    })
    public ResponseEntity<Notificacion> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(notificacionService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Crear una nueva notificación", description = "Registra una nueva notificación en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "notificación creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Notificacion> crear(@Valid @RequestBody NotificacionDTO notificacionDTO) {
        Notificacion notificacion = new Notificacion();
        notificacion.setIdUsuario(notificacionDTO.getIdUsuario());
        notificacion.setMensaje(notificacionDTO.getMensaje());
        notificacion.setTipoNotificacion(notificacionDTO.getTipoNotificacion());
        return ResponseEntity.status(HttpStatus.CREATED).body(notificacionService.save(notificacion));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una notificación", description = "Elimina una notificación del sistema por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "notificación eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "notificación no encontrada")
    })
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        notificacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{idUsuario}")
    @Operation(summary = "Buscar notificación por usuario", description = "Retorna una notificación según su usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "notificación encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "notificación no encontrada")
    })
    public ResponseEntity<List<Notificacion>> buscarPorUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(notificacionService.findByIdUsuario(idUsuario));
    }

    @GetMapping("/tipo/{tipoNotificacion}")
    @Operation(summary = "Buscar por Tipo de notificación", description = "Retorna según Tipo de notificación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "notificación encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "notificación no encontrada")
    })
    public ResponseEntity<List<Notificacion>> buscarPorTipo(@PathVariable String tipoNotificacion) {
        return ResponseEntity.ok(notificacionService.findByTipoNotificacion(tipoNotificacion));
    }

    @GetMapping("/fecha/{fechaNotificacion}")
    @Operation(summary = "Buscar notificación por fecha", description = "Retorna una notificación según fecha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "notificación encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "notificación no encontrada")
    })
    public ResponseEntity<List<Notificacion>> buscarPorFecha(@PathVariable LocalDate fechaNotificacion) {
        return ResponseEntity.ok(notificacionService.findByFechaNotificacion(fechaNotificacion));
    }
}