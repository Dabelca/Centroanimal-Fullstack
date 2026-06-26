package com.centro_animal.ms_animales;

import com.centro_animal.ms_animales.model.Animal;
import com.centro_animal.ms_animales.repository.AnimalRepository;
import com.centro_animal.ms_animales.service.AnimalService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AnimalServiceTest {

    @Autowired
    private AnimalService animalService;

    @MockBean
    private AnimalRepository animalRepository;

    @Test
    @DisplayName("Debe retornar lista de animales")
    void testFindAll(){
        Animal animal = new Animal(1L, "Max", "Perro", "Quiltro", 3, "Juguetón y amoroso", "Disponible");
        when(animalRepository.findAll()).thenReturn(List.of(animal));

        List<Animal> resultado = animalService.findAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Max", resultado.get(0).getNombre());

    }

    @Test
    @DisplayName("Debe retornar animal por ID")
    void testFindById() {
        Animal animal = new Animal(1L, "Max", "Perro", "Quiltro", 3, "Juguetón y amoroso", "Disponible");
        when(animalRepository.findById(1L)).thenReturn(Optional.of(animal));

        Animal resultado = animalService.findById(1L);

        assertNotNull(resultado);
        assertEquals("Max", resultado.getNombre());
        assertEquals("Perro", resultado.getEspecie());
    }

    @Test
    @DisplayName("Debe guardar un animal correctamente")
    void testSave() {
        Animal animal = new Animal(null, "Max", "Perro", "Quiltro", 3, "Juguetón y amoroso", "Disponible");
        Animal animalGuardado = new Animal(1L, "Max", "Perro", "Quiltro", 3, "Juguetón y amoroso", "Disponible");
        when(animalRepository.save(animal)).thenReturn(animalGuardado);

        Animal resultado = animalService.save(animal);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Max", resultado.getNombre());
    }

    @Test
    @DisplayName("Debe retornar animales por estado")
    void testFindByEstado() {
        Animal animal = new Animal(1L, "Max", "Perro", "Quiltro", 3, "Juguetón y amoroso", "Disponible");
        when(animalRepository.findByEstado("Disponible")).thenReturn(List.of(animal));

        List<Animal> resultado = animalService.findByEstado("Disponible");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Disponible", resultado.get(0).getEstado());
    }

    @Test
    @DisplayName("Debe retornar animales por especie")
    void testFindByEspecie() {
        Animal animal = new Animal(1L, "Max", "Perro", "Quiltro", 3, "Juguetón y amoroso", "Disponible");
        when(animalRepository.findByEspecie("Perro")).thenReturn(List.of(animal));

        List<Animal> resultado = animalService.findByEspecie("Perro");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Perro", resultado.get(0).getEspecie());
    }

    @Test
    @DisplayName("Debe eliminar un animal por ID")
    void testDelete() {
        doNothing().when(animalRepository).deleteById(1L);

        animalService.delete(1L);

        verify(animalRepository, times(1)).deleteById(1L);
    }



}
