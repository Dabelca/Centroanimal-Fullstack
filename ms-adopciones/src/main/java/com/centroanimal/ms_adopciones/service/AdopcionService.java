package com.centroanimal.ms_adopciones.service;

import com.centroanimal.ms_adopciones.client.AnimalClient;
import com.centroanimal.ms_adopciones.model.Adopcion;
import com.centroanimal.ms_adopciones.repository.AdopcionRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@Transactional
public class AdopcionService {

    @Autowired
    private AdopcionRepository adopcionRepository;
    private static final Logger log = LoggerFactory.getLogger(AdopcionService.class);

    @Autowired
    private AnimalClient animalClient;

    public List<Adopcion> findAll(){
        log.info("Listando todas las adopciones");
        return adopcionRepository.findAll();
    }

    public Adopcion findById(Long id) {
        log.info("Buscando adopcion con id: {}", id);
        return adopcionRepository.findById(id).get();
    }

    public Adopcion save(Adopcion adopcion) {
        log.info("Creando adopción para animal: {}", adopcion.getIdAnimal());
        try {
            animalClient.buscarPorId(adopcion.getIdAnimal());
        } catch (Exception e) {
            log.warn("Animal no encontrado con id: {}", adopcion.getIdAnimal());
            throw new RuntimeException("El animal con id " + adopcion.getIdAnimal() + " no existe.");
        }
        if (adopcionRepository.existsByIdAnimal(adopcion.getIdAnimal())) {
            log.warn("El animal {} ya fue adoptado", adopcion.getIdAnimal());
            throw new RuntimeException("Este animal ya fue adoptado");
        }
        adopcion.setFechaAdopcion(LocalDate.now());
        adopcion.setEstado("PENDIENTE");
        log.info("Adopcion creada exitosamente");
        return adopcionRepository.save(adopcion);
    }

    public void delete (Long id) {
        log.info("Eliminando adopcion con id: {}", id);
        adopcionRepository.deleteById(id);
        log.info("Adopcion {} eliminada exitosamente", id);
    }

    public List<Adopcion> findByIdUsuario(Long idUsuario) {
        log.info("Buscando adopciones del usuario: {}", idUsuario);
        return adopcionRepository.findByIdUsuario(idUsuario);
    }

    public List<Adopcion> findByIdAnimal(Long idAnimal) {
        log.info("Buscando adopciones del animal: {}", idAnimal);
        return adopcionRepository.findByIdAnimal(idAnimal);
    }

    public List<Adopcion> findByEstado(String estado) {
        log.info("Buscando adopciones por estado: {}", estado);
        return adopcionRepository.findByEstado(estado);
    }

    public Adopcion cambiarEstado(Long id, String estado) {
        log.info("Cambiando estado de adopcion {} a {}", id, estado);
        Adopcion adopcion = adopcionRepository.findById(id).get();
        adopcion.setEstado(estado);
        log.info("Estado de adopcion {} actualizado exitosamente", id);
        return adopcionRepository.save(adopcion);

    }


}
