package com.centroanimal.ms_padrinos.service;

import com.centroanimal.ms_padrinos.model.Padrino;
import com.centroanimal.ms_padrinos.repository.PadrinoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class PadrinoService {

    @Autowired
    private PadrinoRepository padrinoRepository;

    public List<Padrino> findAll() {
        return padrinoRepository.findAll();
    }

    public Padrino findById(Long id) {
        return padrinoRepository.findById(id).get();
    }

    public Padrino save(Padrino padrino) {
        if (padrinoRepository.existsByIdUsuarioAndIdAnimal(padrino.getIdUsuario(), padrino.getIdAnimal())) {
            throw new RuntimeException("Usted ya ha adoptado a este animal");
        }
        padrino.setFechaInicio(LocalDate.now());
        padrino.setEstado(true);
        return padrinoRepository.save(padrino);
    }


    public void delete(Long id) {
        padrinoRepository.deleteById(id);
    }


    public List<Padrino> findByIdUsuario (Long idUsuario) {
        return padrinoRepository.findByIdUsuario(idUsuario);
    }


    public List<Padrino> findByEstado (Boolean estado) {
        return padrinoRepository.findByEstado(estado);
    }

    public Padrino cambiarEstado (Long id, Boolean estado) {
        Padrino padrino = padrinoRepository.findById(id).get();
        padrino.setEstado(estado);
        return padrinoRepository.save(padrino);
    }

    public Padrino actualizarCuota (Long id, Double montoCuota) {
        Padrino padrino = padrinoRepository.findById(id).get();
        padrino.setMontoCuota(montoCuota);
        return padrinoRepository.save(padrino);
    }

    public List<Padrino> findByIdAnimal (Long idAnimal) {
        return padrinoRepository.findByIdAnimal(idAnimal);
    }

}
