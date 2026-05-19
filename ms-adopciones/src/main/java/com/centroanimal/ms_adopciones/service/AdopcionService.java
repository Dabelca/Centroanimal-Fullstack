package com.centroanimal.ms_adopciones.service;

import com.centroanimal.ms_adopciones.model.Adopcion;
import com.centroanimal.ms_adopciones.repository.AdopcionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@Transactional
public class AdopcionService {

    @Autowired
    private AdopcionRepository adopcionRepository;

    public List<Adopcion> findAll(){
        return adopcionRepository.findAll();
    }

    public Adopcion findById(Long id) {
        return adopcionRepository.findById(id).get();
    }

    public Adopcion save(Adopcion adopcion) {
        if (adopcionRepository.existsByIdAnimal(adopcion.getIdAnimal())) {
            throw  new RuntimeException("Este animal ya fue adoptado");
        }
        adopcion.setFechaAdopcion(LocalDate.now());
        adopcion.setEstado("PENDIENTE");
        return adopcionRepository.save(adopcion);
    }

    public void delete (Long id) {
        adopcionRepository.deleteById(id);
    }

    public List<Adopcion> findByIdUsuario(Long idUsuario) {
        return adopcionRepository.findByIdUsuario(idUsuario);
    }

    public List<Adopcion> findByIdAnimal(Long idAnimal) {
        return adopcionRepository.findByIdAnimal(idAnimal);
    }

    public List<Adopcion> findByEstado(String estado) {
        return adopcionRepository.findByEstado(estado);
    }

    public Adopcion cambiarEstado(Long id, String estado) {
        Adopcion adopcion = adopcionRepository.findById(id).get();
        adopcion.setEstado(estado);
        return adopcionRepository.save(adopcion);

    }


}
