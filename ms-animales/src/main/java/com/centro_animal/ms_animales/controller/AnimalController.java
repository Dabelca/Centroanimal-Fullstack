package com.centro_animal.ms_animales.controller;

import com.centro_animal.ms_animales.dto.AnimalDTO;
import com.centro_animal.ms_animales.dto.AnimalUpdateDTO;
import com.centro_animal.ms_animales.model.Animal;
import com.centro_animal.ms_animales.service.AnimalService;
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
@RequestMapping("/api/v1/animales")
@Tag(name = "Animales", description = "Operaciones para la gestión de animales del centro")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping
    @Operation(summary = "Listar todos los animales", description = "Obtiene una lista de todos los animales registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de animales obtenida exitosamente"),
            @ApiResponse(responseCode = "204", description = "No hay animales registrados")
    })
    public ResponseEntity<List<Animal>> listar() {
        List<Animal> animales = animalService.findAll();
        if (animales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(animales);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar animal por ID", description = "Retorna un animal según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Animal no encontrado")
    })
    public ResponseEntity<Animal> buscarPorId(@PathVariable Long id) {
        try {
            Animal animal = animalService.findById(id);
            return ResponseEntity.ok(animal);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/estado/{estado}")
    @Operation(summary = "Buscar animales por estado", description = "Retorna un animal según su estado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente"),
            @ApiResponse(responseCode = "404", description = "No hay animales con ese estado")
    })
    public ResponseEntity<List<Animal>> buscarPorEstado(@PathVariable String estado) {
        List<Animal> animales = animalService.findByEstado(estado);
        if (animales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(animales);
    }

    @GetMapping("/especie/{especie}")
    @Operation(summary = "Buscar animales por especie", description = "Retorna animales filtrados por especie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente"),
            @ApiResponse(responseCode = "404", description = "No hay animales con esa especie")
    })
    public ResponseEntity<List<Animal>> buscarPorEspecie(@PathVariable String especie) {
        List<Animal> animales = animalService.findByEspecie(especie);
        if (animales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(animales);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo animal", description = "Registra un nuevo animal en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Animal creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
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
    @Operation(summary = "Actualizar un animal", description = "Actualiza los datos de un animal existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Animal no encontrado")
    })
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
    @Operation(summary = "Eliminar un animal", description = "Elimina un animal del sistema por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Animal eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Animal no encontrado")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            animalService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}