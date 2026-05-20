package com.centroanimal.ms_padrinos.repository;

import com.centroanimal.ms_padrinos.model.Padrino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PadrinoRepository extends JpaRepository<Padrino, Long> {

    List<Padrino> findByIdUsuario(Long idUsuario);

    List<Padrino> findByIdAnimal(Long idAnimal);

    List<Padrino> findByEstado(Boolean estado);
    Boolean existsByIdUsuarioAndIdAnimal(Long idUsuario, Long idAnimal);

}