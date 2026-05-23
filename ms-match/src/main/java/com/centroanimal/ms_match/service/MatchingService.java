package com.centroanimal.ms_match.service;

import com.centroanimal.ms_match.model.Matching;
import com.centroanimal.ms_match.repository.MatchingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchingService {

    @Autowired
    private MatchingRepository matchingRepository;
    private static final Logger log = LoggerFactory.getLogger(MatchingService.class);

    public List<Matching> findAll() {
        log.info("Listando todos los matches");
        return matchingRepository.findAll();
    }

    public Matching findById(Long id) {
        log.info("Buscando matches con id: {}", id);
        return matchingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matching no encontrado con id: " + id));
    }

    public Matching crear(Matching matching) {
        log.info("Creando nuevo matching para usuario: {}", matching.getIdUsuario());
        if (matching.getIdUsuario() == null) {
            log.warn("Se intento crear un matching sin idUsuario");
            throw new RuntimeException("El idUsuario es obligatorio para realizar un matching.");
        }

        matching.setResultado("PENDIENTE");
        log.info("Matching creado exitosamente");
        return matchingRepository.save(matching);
    }

    public void eliminar(Long id) {
        log.info("Eliminando matching con id: {}", id);
        matchingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matching no encontrado con id: " + id));
        matchingRepository.deleteById(id);
        log.info("Matching {} eliminado exitosamente", id);
    }

    public List<Matching> findByIdUsuario(Long idUsuario) {
        log.info("Buscando matches del usuario: {}", idUsuario);
        return matchingRepository.findByIdUsuario(idUsuario);
    }

    public List<Matching> findByEspeciePreferida(String especiePreferida) {
        log.info("Buscando matches por especie: {}", especiePreferida);
        return matchingRepository.findByEspeciePreferida(especiePreferida);
    }

    public List<Matching> findByTamanoPreferido(String tamanoPreferido) {
        log.info("Buscando matches por tamaño: {}", tamanoPreferido);
        return matchingRepository.findByTamanoPreferido(tamanoPreferido);
    }

    public List<Matching> findByEdadPreferida(Integer edadPreferida) {
        log.info("Buscando matches por edad preferida: {}", edadPreferida);
        return matchingRepository.findByEdadPreferida(edadPreferida);
    }



}