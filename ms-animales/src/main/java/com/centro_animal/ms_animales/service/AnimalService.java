package com.centro_animal.ms_animales.service;

import com.centro_animal.ms_animales.model.Animal;
import com.centro_animal.ms_animales.repository.AnimalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    public Animal findById(Long id) {
        return animalRepository.findById(id).get();
    }

    public Animal save(Animal animal) {
        if (animalRepository.existsByNombreAndEspecie(animal.getNombre(), animal.getEspecie())) {
            throw new RuntimeException("Ya existe un animal con ese nombre y especie");
        }
        return  animalRepository.save(animal);
    }

    public  void delete(Long id){
        animalRepository.deleteById(id);
    }

    public List<Animal> findByEstado(String estado) {
        return animalRepository.findByEstado(estado);
    }

    public List<Animal> findByEspecie(String especie) {
        return animalRepository.findByEspecie(especie);
    }

    public List<Animal> findByRaza(String raza) {
        return animalRepository.findByRaza(raza);
    }

    public List<Animal> findByEdad(Integer edad) {
        return animalRepository.findByEdad(edad);
    }


}
