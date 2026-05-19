package com.centro_animal.ms_animales.controller;

import com.centro_animal.ms_animales.model.Animal;
import com.centro_animal.ms_animales.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/animales")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping
    public ResponseEntity<List<Animal>> listar() {
        List<Animal> animales = animalService.findAll();
        if (animales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(animales);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> buscarPorId(@PathVariable Long id) {
        try {
            Animal animal = animalService.findById(id);
            return ResponseEntity.ok(animal);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Animal>> buscarPorEstado(@PathVariable String estado) {
        List<Animal> animales = animalService.findByEstado(estado);
        if (animales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(animales);
    }

    @GetMapping("/especie/{especie}")
    public ResponseEntity<List<Animal>> buscarPorEspecie(@PathVariable String especie) {
        List<Animal> animales = animalService.findByEspecie(especie);
        if (animales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(animales);
    }

    @PostMapping
    public ResponseEntity<Animal> crear(@RequestBody Animal animal) {
        Animal animalNuevo = animalService.save(animal);
        return ResponseEntity.status(HttpStatus.CREATED).body(animalNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> actualizar(@PathVariable Long id, @RequestBody Animal animal) {
        try {
            Animal ani = animalService.findById(id);
            ani.setNombre(animal.getNombre());
            ani.setEspecie(animal.getEspecie());
            ani.setRaza(animal.getRaza());
            ani.setEdad(animal.getEdad());
            ani.setDescripcion(animal.getDescripcion());
            ani.setEstado(animal.getEstado());
            animalService.save(ani);
            return ResponseEntity.ok(ani);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            animalService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}