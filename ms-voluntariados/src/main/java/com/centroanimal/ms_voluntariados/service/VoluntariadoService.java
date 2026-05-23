package com.centroanimal.ms_voluntariados.service;

import com.centroanimal.ms_voluntariados.model.Voluntariado;
import com.centroanimal.ms_voluntariados.repository.VoluntariadoRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@Transactional
public class VoluntariadoService {

    @Autowired
    private VoluntariadoRepository voluntariadoRepository;
    private static final Logger log = LoggerFactory.getLogger(VoluntariadoService.class);

    public List<Voluntariado>findAll() {
        log.info("Listando todos los voluntariados");
        return voluntariadoRepository.findAll();
    }

    public Voluntariado findById(Long id) {
        log.info("Buscando voluntariado por id: {}", id);
        return voluntariadoRepository.findById(id).get();
    }

    public Voluntariado save(Voluntariado voluntariado) {
        log.info("Creando postulacion para usuario: {}", voluntariado.getIdUsuario());
        if (voluntariadoRepository.existsByIdUsuario(voluntariado.getIdUsuario())) {
            log.warn("El usuario {} ya tiene una postulacion existente", voluntariado.getIdUsuario());
            throw new RuntimeException("Este usuario ya realizo una postulación");
        }
        voluntariado.setFechaPostulacion(LocalDate.now());
        voluntariado.setEstado("PENDIENTE");
        log.info("Postulacion creada exitosamente");
        return voluntariadoRepository.save(voluntariado);
    }

    public void delete(Long id) {
        log.info("Eliminando voluntariado con id: {}", id);
        voluntariadoRepository.deleteById(id);
        log.info("Voluntariado {} eliminado exitosamente", id);
    }

    public List<Voluntariado>findByIdUsuario(Long idUsuario) {
        log.info("Buscando voluntariados del usuario: {}", idUsuario);
        return voluntariadoRepository.findByIdUsuario(idUsuario);

    }

    public List<Voluntariado>findByEstado(String estado) {
        log.info("Buscando voluntariados por estado: {}", estado);
        return voluntariadoRepository.findByEstado(estado);
    }

    public Voluntariado cambiarEstado(Long id, String estado) {
        log.info("Cambiando estado del voluntariado {} a {}", id, estado);
        Voluntariado voluntariado = voluntariadoRepository.findById(id).get();
        voluntariado.setEstado(estado);
        if (estado.equals("APROBADO")) {
            voluntariado.setFechaInscripcion(LocalDate.now());
            log.info("Voluntariado {} aprobado, fecha de inscripcion seteada", id);
        }
        log.info("Estado del voluntariado {} actualizado exitosamente", id);
        return voluntariadoRepository.save(voluntariado);
    }

    public List<Voluntariado>findByFechaPostulacion(LocalDate fechaPostulacion) {
        log.info("Buscando voluntariados por fecha de postulacion: {}", fechaPostulacion);
        return voluntariadoRepository.findByFechaPostulacion(fechaPostulacion);
    }

    public List<Voluntariado>findByFechaInscripcion(LocalDate fechaInscripcion) {
        log.info("Buscando voluntariados por fecha de inscripcion: {}", fechaInscripcion);
        return voluntariadoRepository.findByFechaInscripcion(fechaInscripcion);
    }

}
