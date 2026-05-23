package com.centroanimal.ms_visitas.service;

import com.centroanimal.ms_visitas.model.Visita;
import com.centroanimal.ms_visitas.repository.VisitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;



@Service
public class VisitaService {

    @Autowired
    private VisitaRepository visitaRepository;

    public List<Visita> findAll(){
        return visitaRepository.findAll();
    }

    public Visita findById(Long id){
        return visitaRepository.findById(id).get();
    }

    public Visita crear(Visita visita){
        if (visita.getFechaVisita().isBefore(LocalDate.now())){
            throw new RuntimeException("La fecha de visita no es valida");
        }
        if ("VISITA_ANIMAL".equals(visita.getTipoVisita()) && visita.getIdAnimal() ==null){
            throw new RuntimeException("Debe indicar un animal para visita de tipo VISITA_ANIMAL.");
        }
        if ("VISITA_CENTRO".equals(visita.getTipoVisita()) && visita.getIdAnimal() != null){
            throw new RuntimeException("No requiere indicar un animal para visita de tipo VISITA_CENTRO");
        }
        if (visita.getIdUsuario() != null) {
            boolean existe = visitaRepository.existsByIdUsuarioAndFechaVisitaAndEstadoIn(
                    visita.getIdUsuario(),
                    visita.getFechaVisita(),
                    List.of("PENDIENTE", "CONFIRMADA")
            );
            if (existe) {
                throw new RuntimeException("El usuario ya tiene una visita pendiente o confirmada para esa fecha.");
            }
        }
        visita.setEstado("PENDIENTE");
        return visitaRepository.save(visita);
    }

    public Visita actualizar(Long id, Visita visitaActualizada) {
        Visita visita = visitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visita no encontrada con id: " + id));

        visita.setIdUsuario(visitaActualizada.getIdUsuario());
        visita.setIdAnimal(visitaActualizada.getIdAnimal());
        visita.setTipoVisita(visitaActualizada.getTipoVisita());
        visita.setFechaVisita(visitaActualizada.getFechaVisita());
        visita.setEstado(visitaActualizada.getEstado());

        return visitaRepository.save(visita);
    }

    public Visita cancelar(Long id) {
        Visita visita = visitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visita no encontrada con id: " + id));

        if ("CANCELADA".equals(visita.getEstado())) {
            throw new RuntimeException("La visita ya está cancelada.");
        }

        if ("COMPLETADA".equals(visita.getEstado())) {
            throw new RuntimeException("No se puede cancelar una visita ya completada.");
        }

        visita.setEstado("CANCELADA");
        return visitaRepository.save(visita);
    }

    public Visita confirmar(Long id) {
        Visita visita = visitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visita no encontrada con id: " + id));

        if ("CANCELADA".equals(visita.getEstado())) {
            throw new RuntimeException("No se puede confirmar una visita cancelada.");
        }

        if ("COMPLETADA".equals(visita.getEstado())) {
            throw new RuntimeException("No se puede confirmar una visita ya completada.");
        }

        if ("CONFIRMADA".equals(visita.getEstado())) {
            throw new RuntimeException("La visita ya está confirmada.");
        }

        visita.setEstado("CONFIRMADA");
        return visitaRepository.save(visita);
    }

    public List<Visita> findByIdUsuario(Long idUsuario){
        return visitaRepository.findByIdUsuario(idUsuario);
    }

    public List<Visita> findByIdAnimal(Long idAnimal){
        return visitaRepository.findByIdAnimal(idAnimal);
    }

    public List<Visita> findByTipoVisita(String tipoVisita) {
        return visitaRepository.findByTipoVisita(tipoVisita);
    }


    public List<Visita> findByFechaVisita(LocalDate fechaVisita){
        return visitaRepository.findByFechaVisita(fechaVisita);
    }

    public List<Visita> findByEstado(String estado){
        return visitaRepository.findByEstado(estado);
    }


}
