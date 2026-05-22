package com.centroanimal.ms_visitas.service;

import com.centroanimal.ms_visitas.model.Visita;
import com.centroanimal.ms_visitas.repository.VisitaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;



@Service
public class VisitaService {

    @Autowired
    private VisitaRepository visitaRepository;
    private static final Logger log = LoggerFactory.getLogger(VisitaService.class);

    public List<Visita> findAll(){
        log.info("Listando todas las visitas");
        return visitaRepository.findAll();
    }

    public Visita findById(Long id){
        log.info("Buscando visitas por el id: {}", id);
        return visitaRepository.findById(id).get();
    }

    public Visita crear(Visita visita){
        log.info("Creando una nueva visita de tipo: {}", visita.getTipoVisita());
        if (visita.getFechaVisita().isBefore(LocalDate.now())){
            log.warn("Fecha de visita invalida: {}", visita.getFechaVisita());
            throw new RuntimeException("La fecha de visita no es valida");
        }
        if ("VISITA_ANIMAL".equals(visita.getTipoVisita()) && visita.getIdAnimal() ==null){
            log.warn("Visita de tipo VISITA_ANIMAL sin idAnimal");
            throw new RuntimeException("Debe indicar un animal para visita de tipo VISITA_ANIMAL.");
        }
        if ("VISITA_CENTRO".equals(visita.getTipoVisita()) && visita.getIdAnimal() != null){log.warn("Visita de tipo VISITA_CENTRO no requiere idAnimal");
            throw new RuntimeException("No requiere indicar un animal para visita de tipo VISITA_CENTRO");
        }
        if (visita.getIdUsuario() != null) {
            boolean existe = visitaRepository.existsByIdUsuarioAndFechaVisitaAndEstadoIn(
                    visita.getIdUsuario(),
                    visita.getFechaVisita(),
                    List.of("PENDIENTE", "CONFIRMADA")
            );
            if (existe) {
                log.warn("El usuario {} ya tiene visita en la fecha {}", visita.getIdUsuario(), visita.getFechaVisita());
                throw new RuntimeException("El usuario ya tiene una visita pendiente o confirmada para esa fecha.");
            }
        }
        visita.setEstado("PENDIENTE");
        log.info("Visita creada exitosamente");
        return visitaRepository.save(visita);
    }

    public Visita actualizar(Long id, Visita visitaActualizada) {
        log.info("Actualizando visita con id: {}", id);
        Visita visita = visitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visita no encontrada con id: " + id));

        visita.setIdUsuario(visitaActualizada.getIdUsuario());
        visita.setIdAnimal(visitaActualizada.getIdAnimal());
        visita.setTipoVisita(visitaActualizada.getTipoVisita());
        visita.setFechaVisita(visitaActualizada.getFechaVisita());
        visita.setEstado(visitaActualizada.getEstado());

        log.info("Visita actualizada exitosamente");
        return visitaRepository.save(visita);
    }

    public Visita cancelar(Long id) {
        log.info("Cancelando visita con id: {}", id);
        Visita visita = visitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visita no encontrada con id: " + id));

        if ("CANCELADA".equals(visita.getEstado())) {
            log.warn("La visita {} ya estaba cancelada", id);
            throw new RuntimeException("La visita ya está cancelada.");
        }

        if ("COMPLETADA".equals(visita.getEstado())) {
            log.warn("No se puede cancelar visita completada: {}", id);
            throw new RuntimeException("No se puede cancelar una visita ya completada.");
        }

        visita.setEstado("CANCELADA");
        log.info("Visita {} cancelada exitosamente", id);
        return visitaRepository.save(visita);
    }

    public Visita confirmar(Long id) {
        log.info("Confirmando visita con id: {}", id);
        Visita visita = visitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visita no encontrada con id: " + id));

        if ("CANCELADA".equals(visita.getEstado())) {
            log.warn("No se puede confirmar.Esta visita está cancelada: {}", id);
            throw new RuntimeException("No se puede confirmar una visita cancelada.");
        }

        if ("COMPLETADA".equals(visita.getEstado())) {
            log.warn("No se puede confirmar. Esta visita está completada: {}", id);
            throw new RuntimeException("No se puede confirmar una visita ya completada.");
        }

        if ("CONFIRMADA".equals(visita.getEstado())) {
            log.warn("La visita {} ya esta confirmada", id);
            throw new RuntimeException("La visita ya esta confirmada.");
        }

        visita.setEstado("CONFIRMADA");
        log.info("Visita {} confirmada exitosamente", id);
        return visitaRepository.save(visita);
    }

    public List<Visita> findByIdUsuario(Long idUsuario){
        log.info("Buscando visitas del usuario: {}", idUsuario);
        return visitaRepository.findByIdUsuario(idUsuario);
    }

    public List<Visita> findByIdAnimal(Long idAnimal){
        log.info("Buscando visitas del animal: {}", idAnimal);
        return visitaRepository.findByIdAnimal(idAnimal);
    }

    public List<Visita> findByTipoVisita(String tipoVisita) {
        log.info("Buscando visitas por tipo: {}", tipoVisita);
        return visitaRepository.findByTipoVisita(tipoVisita);
    }


    public List<Visita> findByFechaVisita(LocalDate fechaVisita){
        log.info("Buscando visitas por fecha: {}", fechaVisita);
        return visitaRepository.findByFechaVisita(fechaVisita);
    }

    public List<Visita> findByEstado(String estado){
        log.info("Buscando visitas por estado: {}", estado);
        return visitaRepository.findByEstado(estado);
    }


}
