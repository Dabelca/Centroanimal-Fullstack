package com.centroanimal.ms_notificaciones.service;

import com.centroanimal.ms_notificaciones.model.Notificacion;
import com.centroanimal.ms_notificaciones.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    public List<Notificacion> findAll() {
        return notificacionRepository.findAll();
    }

    public Notificacion findById(Long id) {
        return notificacionRepository.findById(id).get();
    }

    public List<Notificacion> findByIdUsuario(Long idUsuario) {
        return notificacionRepository.findByIdUsuario(idUsuario);
    }

    public Notificacion save(Notificacion notificacion) {
        if (notificacion.getIdUsuario() == null) {
            throw new RuntimeException("El idUsuario es obligatorio.");
        }
        if (notificacion.getMensaje() == null || notificacion.getMensaje().isEmpty()) {
            throw new RuntimeException("El mensaje es obligatorio.");
        }
        if (!List.of("ADOPCION", "VISITA", "MATCHING", "DONACION").contains(notificacion.getTipoNotificacion())) {
            throw new RuntimeException("Tipo de notificacion no valido.");
        }
        notificacion.setFechaNotificacion(LocalDate.now());
        return notificacionRepository.save(notificacion);
    }

    public void eliminar(Long id){
        notificacionRepository.deleteById(id);
    }

    public List<Notificacion> findByTipoNotificacion(String tipoNotificacion){
        return notificacionRepository.findByTipoNotificacion(tipoNotificacion);

    }

    public List<Notificacion> findByFechaNotificacion(LocalDate fechaNotificacion) {
        return notificacionRepository.findByFechaNotificacion(fechaNotificacion);
    }


}


