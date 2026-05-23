package com.centroanimal.ms_notificaciones.service;

import com.centroanimal.ms_notificaciones.model.Notificacion;
import com.centroanimal.ms_notificaciones.repository.NotificacionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;
    private static final Logger log = LoggerFactory.getLogger(NotificacionService.class);

    public List<Notificacion> findAll() {
        log.info("Listando todas las notificaciones");
        return notificacionRepository.findAll();
    }

    public Notificacion findById(Long id) {
        log.info("Buscando notificacion por id: {}", id);
        return notificacionRepository.findById(id).get();
    }

    public List<Notificacion> findByIdUsuario(Long idUsuario) {
        log.info("Buscando notificaciones por usuario: {}", idUsuario);
        return notificacionRepository.findByIdUsuario(idUsuario);
    }

    public Notificacion save(Notificacion notificacion) {
        log.info("Creando nueva notificacion de tipo: {}", notificacion.getTipoNotificacion());
        if (notificacion.getIdUsuario() == null) {
            log.warn("Se intento crear una notificacion sin idUsuario");
            throw new RuntimeException("El idUsuario es obligatorio.");
        }
        if (notificacion.getMensaje() == null || notificacion.getMensaje().isEmpty()) {
            log.warn("Se intento crear una notificacion sin mensaje");
            throw new RuntimeException("El mensaje es obligatorio.");
        }
        if (!List.of("ADOPCION", "VISITA", "MATCHING", "DONACION").contains(notificacion.getTipoNotificacion())) {
            log.warn("Tipo de notificacion invalido: {}", notificacion.getTipoNotificacion());
            throw new RuntimeException("Tipo de notificacion no valido.");
        }
        notificacion.setFechaNotificacion(LocalDate.now());
        log.info("Notificacion creada exitosamente");
        return notificacionRepository.save(notificacion);

    }

    public void eliminar(Long id){
        log.info("Eliminando notificacion con id: {}", id);
        notificacionRepository.deleteById(id);
        log.info("Notificacion {} eliminada exitosamente", id);
    }

    public List<Notificacion> findByTipoNotificacion(String tipoNotificacion){
        log.info("Buscando notificaciones por tipo: {}", tipoNotificacion);
        return notificacionRepository.findByTipoNotificacion(tipoNotificacion);

    }

    public List<Notificacion> findByFechaNotificacion(LocalDate fechaNotificacion) {
        log.info("Buscando notificaciones por fecha: {}", fechaNotificacion);
        return notificacionRepository.findByFechaNotificacion(fechaNotificacion);
    }


}


