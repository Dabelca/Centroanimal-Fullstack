package com.centroanimal.ms_voluntariados.service;

import com.centroanimal.ms_voluntariados.model.Voluntariado;
import com.centroanimal.ms_voluntariados.repository.VoluntariadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@Transactional
public class VoluntariadoService {

    @Autowired
    private VoluntariadoRepository voluntariadoRepository;

    public List<Voluntariado>findAll() {
        return voluntariadoRepository.findAll();
    }

    public Voluntariado findById(Long id) {
        return voluntariadoRepository.findById(id).get();
    }

    public Voluntariado save(Voluntariado voluntariado) {
        if (voluntariadoRepository.existsByIdUsuario(voluntariado.getIdUsuario())) {
            throw new RuntimeException("Este usuario ya realizo una postulación");
        }
        voluntariado.setFechaPostulacion(LocalDate.now());
        voluntariado.setEstado("PENDIENTE");
        return voluntariadoRepository.save(voluntariado);
    }

    public void delete(Long id) {
        voluntariadoRepository.deleteById(id);
    }

    public List<Voluntariado>findByIdUsuario(Long idUsuario) {
        return voluntariadoRepository.findByIdUsuario(idUsuario);

    }

    public List<Voluntariado>findByEstado(String estado) {
        return voluntariadoRepository.findByEstado(estado);
    }

    public Voluntariado cambiarEstado(Long id, String estado) {
        Voluntariado voluntariado = voluntariadoRepository.findById(id).get();
        voluntariado.setEstado(estado);
        if (estado.equals("APROBADO")) {
            voluntariado.setFechaInscripcion(LocalDate.now());
        }
        return voluntariadoRepository.save(voluntariado);
    }

    public List<Voluntariado>findByFechaPostulacion(LocalDate fechaPostulacion) {
        return voluntariadoRepository.findByFechaPostulacion(fechaPostulacion);
    }

    public List<Voluntariado>findByFechaInscripcion(LocalDate fechaInscripcion) {
        return voluntariadoRepository.findByFechaInscripcion(fechaInscripcion);
    }

}
