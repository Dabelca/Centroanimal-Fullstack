package com.centroanimal.ms_match.service;

import com.centroanimal.ms_match.model.Matching;
import com.centroanimal.ms_match.repository.MatchingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchingService {

    @Autowired
    private MatchingRepository matchingRepository;

    public List<Matching> findAll() {
        return matchingRepository.findAll();
    }

    public Matching findById(Long id) {
        return matchingRepository.findById(id).get();
    }

    public Matching crear(Matching matching) {
        if (matching.getIdUsuario() == null) {
            throw new RuntimeException("El idUsuario es obligatorio para realizar un matching.");
        }

        matching.setResultado("PENDIENTE");

        return matchingRepository.save(matching);
    }

    public void eliminar(Long id) {
        matchingRepository.deleteById(id);
    }

    public List<Matching> findByIdUsuario(Long idUsuario) {
        return matchingRepository.findByIdUsuario(idUsuario);
    }

    public List<Matching> findByEspeciePreferida(String especiePreferida) {
        return matchingRepository.findByEspeciePreferida(especiePreferida);
    }

    public List<Matching> findByTamanoPreferido(String tamanoPreferido) {
        return matchingRepository.findByTamanoPreferido(tamanoPreferido);
    }

    public List<Matching> findByEdadPreferida(Integer edadPreferida) {
        return matchingRepository.findByEdadPreferida(edadPreferida);
    }



}