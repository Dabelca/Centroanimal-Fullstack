package com.centroanimal.ms_voluntariados.controller;



import com.centroanimal.ms_voluntariados.model.Voluntariado;
import com.centroanimal.ms_voluntariados.service.VoluntariadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/voluntariados")
public class VoluntariadoController {

    @Autowired
    private VoluntariadoService voluntariadoService;


    @GetMapping
    public ResponseEntity<List<Voluntariado>> listar() {
        List<Voluntariado> voluntariados = voluntariadoService.findAll();
        if (voluntariados.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.ok(voluntariados);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Voluntariado> buscarPoId(@PathVariable Long id) {
        try {
            Voluntariado voluntariado = voluntariadoService.findById(id);
            return ResponseEntity.ok(voluntariado);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Voluntariado>> buscarPorIdUsuario(@PathVariable Long idUsuario) {
        List<Voluntariado> voluntariados = voluntariadoService.findByIdUsuario(idUsuario);
        if (voluntariados.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(voluntariados);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Voluntariado>> buscarPorEstado(@PathVariable String estado) {
        List<Voluntariado> voluntariados = voluntariadoService.findByEstado(estado);
        if (voluntariados.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(voluntariados);
    }

    @GetMapping("/fechaPostulacion/{fechaPostulacion}")
    public ResponseEntity<List<Voluntariado>> buscarPorFechaPostulacion(@PathVariable LocalDate fechaPostulacion){
        List<Voluntariado> voluntariados = voluntariadoService.findByFechaPostulacion(fechaPostulacion);
        if (voluntariados.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(voluntariados);
    }

    @GetMapping("/fechaInscripcion/{fechaIncripcion}")
    public ResponseEntity<List<Voluntariado>> buscarPorFechaInscripcion(@PathVariable LocalDate fechaInscripcion){
        List<Voluntariado> voluntariados = voluntariadoService.findByFechaInscripcion(fechaInscripcion);
        if (voluntariados.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(voluntariados);
    }

    @PostMapping
    public ResponseEntity<Voluntariado> crear(@RequestBody Voluntariado voluntariado) {
        try {
            Voluntariado nuevo = voluntariadoService.save(voluntariado);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Voluntariado> cambiarEstado(@PathVariable Long id, @RequestParam String estado) {
        try {
            Voluntariado voluntariado = voluntariadoService.cambiarEstado(id, estado);
            return ResponseEntity.ok(voluntariado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            voluntariadoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();

        }
    }




}




