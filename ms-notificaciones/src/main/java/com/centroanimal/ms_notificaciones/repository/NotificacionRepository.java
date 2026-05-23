package com.centroanimal.ms_notificaciones.repository;

import com.centroanimal.ms_notificaciones.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

    List<Notificacion> findByIdUsuario(Long idUsuario);

    List<Notificacion> findByTipoNotificacion(String tipoNotificacion);

    List<Notificacion> findByFechaNotificacion(LocalDate fechaNotificacion);

}
