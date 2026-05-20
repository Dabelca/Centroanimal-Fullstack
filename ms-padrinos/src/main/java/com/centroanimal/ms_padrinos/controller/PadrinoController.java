package com.centroanimal.ms_padrinos.controller;

import com.centroanimal.ms_padrinos.model.Padrino;
import com.centroanimal.ms_padrinos.service.PadrinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/padrino")
public class PadrinoController {

    @Autowired
    private PadrinoService padrinoService;

    @GetMapping
    public ResponseEntity<List<Padrino>> listar() {
        List<Padrino> padrinos = padrinoService.findAll();
        if (padrinos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<Padrino> PadrinoService;
        return ResponseEntity.ok(padrinos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Padrino> buscarPorId(@PathVariable Long id) {
        try {
            Padrino padrino = padrinoService.findById(id);
            return ResponseEntity.ok(padrino);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Padrino>> buscarPorUsuario(@PathVariable Long idUsuario) {
        List<Padrino> padrinos = padrinoService.findByIdUsuario(idUsuario);
        if (padrinos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(padrinos);
    }

    @GetMapping("/animal/{idAnimal}")
    public ResponseEntity<List<Padrino>> buscarPorAnimal(@PathVariable Long idAnimal) {
        List<Padrino> padrinos = padrinoService.findByIdAnimal(idAnimal);
        if (padrinos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(padrinos);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Padrino>> buscarPorEstado(@PathVariable Boolean estado) {
        List<Padrino> padrinos = padrinoService.findByEstado(estado);
        if (padrinos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(padrinos);
    }

    @PostMapping
    public ResponseEntity<Padrino> crear(@RequestBody Padrino padrino) {
        try {
            Padrino nuevoPadrino = padrinoService.save(padrino);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPadrino);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Padrino> cambiarEstado(@PathVariable Long id, @RequestParam Boolean estado) {
        try {
            Padrino padrino = padrinoService.cambiarEstado(id, estado);
            return ResponseEntity.ok(padrino);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/cuota")
    public ResponseEntity<Padrino> actualizarCuota(@PathVariable Long id, @RequestParam Double montoCuota) {
        try {
            Padrino padrino = padrinoService.actualizarCuota(id, montoCuota);
            return ResponseEntity.ok(padrino);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            padrinoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}






