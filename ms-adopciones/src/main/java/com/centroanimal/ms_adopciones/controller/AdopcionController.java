package com.centroanimal.ms_adopciones.controller;

import com.centroanimal.ms_adopciones.model.Adopcion;
import com.centroanimal.ms_adopciones.service.AdopcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/adopciones")
public class AdopcionController {

    @Autowired
    private AdopcionService adopcionService;

    @GetMapping
    public ResponseEntity<List<Adopcion>> listar(){
        List<Adopcion> adopciones = adopcionService.findAll();
        if (adopciones.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(adopciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adopcion> buscarPorId(@PathVariable Long id) {
        try {
            Adopcion adopcion = adopcionService.findById(id);
            return ResponseEntity.ok(adopcion);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Adopcion>> buscarPorUsuario(@PathVariable Long idUsuario) {
        List<Adopcion> adopciones = adopcionService.findByIdUsuario(idUsuario);
        if (adopciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(adopciones);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Adopcion>> buscarPorEstado(@PathVariable String estado) {
        List<Adopcion> adopciones = adopcionService.findByEstado(estado);
        if (adopciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(adopciones);
    }

    @PostMapping
    public ResponseEntity<Adopcion> crear(@RequestBody Adopcion adopcion) {
        try {
            Adopcion nuevaAdopcion = adopcionService.save(adopcion);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaAdopcion);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Adopcion> cambiarEstado(@PathVariable Long id, @RequestParam String estado) {
        try {
            Adopcion adopcion = adopcionService.cambiarEstado(id, estado);
            return ResponseEntity.ok(adopcion);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            adopcionService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}




}







