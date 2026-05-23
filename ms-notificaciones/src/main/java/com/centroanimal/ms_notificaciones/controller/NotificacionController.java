package com.centroanimal.ms_notificaciones.controller;

import com.centroanimal.ms_notificaciones.model.Notificacion;
import com.centroanimal.ms_notificaciones.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping
    public ResponseEntity<List<Notificacion>> buscarTodas() {
        return ResponseEntity.ok(notificacionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(notificacionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Notificacion> crear(@RequestBody Notificacion notificacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(notificacionService.save(notificacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        notificacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Notificacion>> buscarPorUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(notificacionService.findByIdUsuario(idUsuario));
    }

    @GetMapping("/tipo/{tipoNotificacion}")
    public ResponseEntity<List<Notificacion>> buscarPorTipo(@PathVariable String tipoNotificacion) {
        return ResponseEntity.ok(notificacionService.findByTipoNotificacion(tipoNotificacion));
    }

    @GetMapping("/fecha/{fechaNotificacion}")
    public ResponseEntity<List<Notificacion>> buscarPorFecha(@PathVariable LocalDate fechaNotificacion) {
        return ResponseEntity.ok(notificacionService.findByFechaNotificacion(fechaNotificacion));
    }
}