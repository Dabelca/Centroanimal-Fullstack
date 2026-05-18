package com.centro_animal.ms_animales.repository;

import com.centro_animal.ms_animales.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findByEstado(String estado);

    List<Animal> findByEspecie(String especie);

    List<Animal> findByRaza(String raza);

    List<Animal> findByEdad(Integer edad);

    boolean existsByNombreAndEspecie(String nombre, String especie);
}
