package com.centroanimal.ms_padrinos.service;

import com.centroanimal.ms_padrinos.client.AnimalClient;
import com.centroanimal.ms_padrinos.client.AnimalClientService;
import com.centroanimal.ms_padrinos.model.Padrino;
import com.centroanimal.ms_padrinos.repository.PadrinoRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class PadrinoService {

    @Autowired
    private PadrinoRepository padrinoRepository;
    private static final Logger log = LoggerFactory.getLogger(PadrinoService.class);

    @Autowired
    private AnimalClientService animalClientService;

    public List<Padrino> findAll() {
        log.info("Listando todos los padrinos");
        return padrinoRepository.findAll();
    }

    public Padrino findById(Long id) {
        log.info("Buscando padrino con id: {}", id);
        return padrinoRepository.findById(id).get();
    }

    public Padrino save(Padrino padrino) {
        log.info("El usuario: {} es nuevo padrino para el animal: {}", padrino.getIdUsuario(), padrino.getIdAnimal());
        animalClientService.verificarAnimalExiste(padrino.getIdAnimal());
        if (padrinoRepository.existsByIdUsuarioAndIdAnimal(padrino.getIdUsuario(), padrino.getIdAnimal())) {
            log.warn("El usuario {} ya es padrino del animal {}", padrino.getIdUsuario(), padrino.getIdAnimal());
            throw new RuntimeException("Usted ya ha adoptado a este animal");
        }
        padrino.setFechaInicio(LocalDate.now());
        padrino.setEstado("ACTIVO");
        log.info("Padrino creado exitosamente");
        return padrinoRepository.save(padrino);
    }


    public void delete(Long id) {
        log.info("Eliminando padrino con id: {}", id);
        padrinoRepository.deleteById(id);
        log.info("Padrino {} eliminado exitosamente", id);
    }


    public List<Padrino> findByIdUsuario (Long idUsuario) {
        log.info("Buscando padrinos del usuario: {}", idUsuario);
        return padrinoRepository.findByIdUsuario(idUsuario);
    }


    public List<Padrino> findByEstado (String  estado) {
        log.info("Buscando padrinos por estado: {}", estado);
        return padrinoRepository.findByEstado(estado);
    }

    public Padrino cambiarEstado (Long id, String estado) {
        log.info("Cambiando estado del padrino {} a {}", id, estado);
        Padrino padrino = padrinoRepository.findById(id).get();
        padrino.setEstado(estado);
        log.info("Estado del padrino {} actualizado exitosamente", id);
        return padrinoRepository.save(padrino);
    }

    public Padrino actualizarCuota (Long id, Double montoCuota) {
        log.info("Actualizando cuota del padrino {} a {}", id, montoCuota);
        Padrino padrino = padrinoRepository.findById(id).get();
        padrino.setMontoCuota(montoCuota);
        log.info("Cuota del padrino {} actualizada exitosamente", id);
        return padrinoRepository.save(padrino);
    }

    public List<Padrino> findByIdAnimal (Long idAnimal) {
        log.info("Buscando padrinos del animal: {}", idAnimal);
        return padrinoRepository.findByIdAnimal(idAnimal);
    }

}
