package com.centroanimal.ms_adopciones.repository;

import com.centroanimal.ms_adopciones.model.Adopcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdopcionRepository extends JpaRepository<Adopcion, Long> {

    List<Adopcion> findByIdUsuario(Long idUsuario);

    List<Adopcion> findByIdAnimal(Long idAnimal);

    List<Adopcion> findByEstado(String estado);

    boolean existsByIdAnimal(Long idAnimal);

}