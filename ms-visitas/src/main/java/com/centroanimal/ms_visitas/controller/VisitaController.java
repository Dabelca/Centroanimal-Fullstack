package com.centroanimal.ms_visitas.controller;

import com.centroanimal.ms_visitas.dto.VisitaDTO;
import com.centroanimal.ms_visitas.model.Visita;
import com.centroanimal.ms_visitas.service.VisitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/visitas")
public class VisitaController {

    @Autowired
    private VisitaService visitaService;

    @GetMapping
    public ResponseEntity<List<Visita>> buscarTodas() {
        return ResponseEntity.ok(visitaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visita> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(visitaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Visita> crear(@Valid @RequestBody VisitaDTO visitaDTO) {
        Visita visita = new Visita();
        visita.setIdUsuario(visitaDTO.getIdUsuario());
        visita.setIdAnimal(visitaDTO.getIdAnimal());
        visita.setTipoVisita(visitaDTO.getTipoVisita());
        visita.setFechaVisita(visitaDTO.getFechaVisita());
        return ResponseEntity.status(HttpStatus.CREATED).body(visitaService.crear(visita));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Visita> actualizar(@PathVariable Long id, @RequestBody Visita visita) {
        return ResponseEntity.ok(visitaService.actualizar(id, visita));
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Visita> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(visitaService.cancelar(id));
    }

    @PutMapping("/{id}/confirmar")
    public ResponseEntity<Visita> confirmar(@PathVariable Long id) {
        return ResponseEntity.ok(visitaService.confirmar(id));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Visita>> buscarPorUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(visitaService.findByIdUsuario(idUsuario));
    }

    @GetMapping("/animal/{idAnimal}")
    public ResponseEntity<List<Visita>> buscarPorAnimal(@PathVariable Long idAnimal) {
        return ResponseEntity.ok(visitaService.findByIdAnimal(idAnimal));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Visita>> buscarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(visitaService.findByEstado(estado));
    }

    @GetMapping("/fecha/{fechaVisita}")
    public ResponseEntity<List<Visita>> buscarPorFecha(@PathVariable LocalDate fechaVisita) {
        return ResponseEntity.ok(visitaService.findByFechaVisita(fechaVisita));
    }

    @GetMapping("/tipo/{tipoVisita}")
    public ResponseEntity<List<Visita>> buscarPorTipo(@PathVariable String tipoVisita) {
        return ResponseEntity.ok(visitaService.findByTipoVisita(tipoVisita));
    }
}