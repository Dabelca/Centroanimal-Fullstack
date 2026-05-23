package com.centro_animal.ms_animales.service;

import com.centro_animal.ms_animales.model.Animal;
import com.centro_animal.ms_animales.repository.AnimalRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;
    private static final Logger log = LoggerFactory.getLogger(AnimalService.class);

    public List<Animal> findAll() {
        log.info("Listando todos los animales");
        return animalRepository.findAll();
    }

    public Animal findById(Long id) {
        log.info("Buscando animal con id: {}", id);
        return animalRepository.findById(id).get();
    }

    public Animal save(Animal animal) {
        log.info("Guardando animal: {} de especie: {}", animal.getNombre(), animal.getEspecie());
        if (animalRepository.existsByNombreAndEspecie(animal.getNombre(), animal.getEspecie())) {
            log.warn("Ya existe un animal con nombre {} y especie {}", animal.getNombre(), animal.getEspecie());
            throw new RuntimeException("Ya existe un animal con ese nombre y especie");
        }
        log.info("Animal guardado exitosamente");
        return  animalRepository.save(animal);
    }

    public  void delete(Long id){
        log.info("Eliminando animal con id: {}", id);
        animalRepository.deleteById(id);
        log.info("Animal {} eliminado exitosamente", id);
    }

    public List<Animal> findByEstado(String estado) {
        log.info("Buscando animales por estado: {}", estado);
        return animalRepository.findByEstado(estado);
    }

    public List<Animal> findByEspecie(String especie) {
        log.info("Buscando animales por especie: {}", especie);
        return animalRepository.findByEspecie(especie);
    }

    public List<Animal> findByRaza(String raza) {
        log.info("Buscando animales por raza: {}", raza);
        return animalRepository.findByRaza(raza);
    }

    public List<Animal> findByEdad(Integer edad) {
        log.info("Buscando animales por edad: {}", edad);
        return animalRepository.findByEdad(edad);
    }


}
