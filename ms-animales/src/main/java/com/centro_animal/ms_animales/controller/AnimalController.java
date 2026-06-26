package com.centro_animal.ms_animales.controller;

import com.centro_animal.ms_animales.dto.AnimalDTO;
import com.centro_animal.ms_animales.dto.AnimalUpdateDTO;
import com.centro_animal.ms_animales.model.Animal;
import com.centro_animal.ms_animales.service.AnimalService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Animal> crear(@Valid @RequestBody AnimalDTO animalDTO) {
        Animal animal = new Animal();
        animal.setNombre(animalDTO.getNombre());
        animal.setEspecie(animalDTO.getEspecie());
        animal.setRaza(animalDTO.getRaza());
        animal.setEdad(animalDTO.getEdad());
        animal.setEstado(animalDTO.getEstado());
        return ResponseEntity.status(HttpStatus.CREATED).body(animalService.save(animal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> actualizar(@PathVariable Long id, @Valid @RequestBody AnimalUpdateDTO animalUpdateDTO) {
        try {
            Animal ani = animalService.findById(id);
            ani.setNombre(animalUpdateDTO.getNombre());
            ani.setEspecie(animalUpdateDTO.getEspecie());
            ani.setRaza(animalUpdateDTO.getRaza());
            ani.setEdad(animalUpdateDTO.getEdad());
            ani.setDescripcion(animalUpdateDTO.getDescripcion());
            ani.setEstado(animalUpdateDTO.getEstado());
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