package com.centroanimal.ms_donaciones.repository;

import com.centroanimal.ms_donaciones.model.Donacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonacionRepository extends JpaRepository<Donacion, Long> {

    List<Donacion> findByIdUsuario(Long idUsuario);

    List<Donacion> findByMontoGreaterThanEqual(Double monto);

}
