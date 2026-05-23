package com.centroanimal.ms_voluntariados.repository;

import com.centroanimal.ms_voluntariados.model.Voluntariado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VoluntariadoRepository extends JpaRepository<Voluntariado, Long> {

   List<Voluntariado> findByIdUsuario(Long idUsuario);

   List<Voluntariado> findByEstado(String estado);

   List<Voluntariado> findByFechaPostulacion(LocalDate fechaPostulacion);

   List<Voluntariado> findByFechaInscripcion(LocalDate fechaInscripcion);

   Boolean existsByIdUsuario(Long idUsuario);


}
