package com.centroanimal.ms_donaciones.service;

import com.centroanimal.ms_donaciones.model.Donacion;
import com.centroanimal.ms_donaciones.repository.DonacionRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class DonacionService {

    @Autowired
    private DonacionRepository donacionRepository;
    private static final Logger log = LoggerFactory.getLogger(DonacionService.class);

    public List<Donacion> findAll() {
        log.info("Listando todas las donaciones");
        return donacionRepository.findAll();
    }

    public Donacion findById(Long id) {
        log.info("Buscando donacion con id: {}", id);
        return donacionRepository.findById(id).get();

    }

    public Donacion save(Donacion donacion) {
        log.info("Creando donación");
        if (donacion.getMonto() <= 0) {
            log.warn("Monto invalido: {}", donacion.getMonto());
            throw new RuntimeException("El monto debe ser mayor a 0");
        }
        if (donacion.getIdUsuario() == null) {
            log.info("Esta donación es anonima");
            if (donacion.getMensaje() != null) {
                donacion.setMensaje("Donación anónima - " + donacion.getMensaje());
            } else {
                donacion.setMensaje("Donación anónima");
            }
        }
        donacion.setFecha(LocalDate.now());
        log.info("Donación creada exitosamente");
        return donacionRepository.save(donacion);
    }


    public List<Donacion> findByIdUsuario(Long idUsuario) {
        log.info("Buscando donaciones del usuario: {}", idUsuario);
        return donacionRepository.findByIdUsuario(idUsuario);
    }

    public List<Donacion> findByMontoGreaterThanEqual(Double monto) {
        log.info("Buscando donaciones con monto mayor o igual a: {}", monto);
        return donacionRepository.findByMontoGreaterThanEqual(monto);
    }




}
