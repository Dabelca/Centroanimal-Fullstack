package com.centroanimal.ms_visitas.repository;

import com.centroanimal.ms_visitas.model.Visita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VisitaRepository extends JpaRepository<Visita, Long> {

    List<Visita> findByIdUsuario(Long idUsuario);

    List<Visita> findByIdAnimal(Long idAnimal);

    List<Visita> findByTipoVisita(String tipoVisita);

    List<Visita> findByFechaVisita(LocalDate fechaVisita);

    List<Visita> findByEstado(String estado);

    boolean existsByIdUsuarioAndFechaVisitaAndEstadoIn(Long idUsuario, LocalDate fechaVisita, List<String> estados);

}
