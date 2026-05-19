package com.centroanimal.ms_donaciones.service;

import com.centroanimal.ms_donaciones.model.Donacion;
import com.centroanimal.ms_donaciones.repository.DonacionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class DonacionService {

    @Autowired
    private DonacionRepository donacionRepository;

    public List<Donacion> findAll() {
        return donacionRepository.findAll();
    }

    public Donacion findById(Long id) {
        return donacionRepository.findById(id).get();

    }

    public Donacion save(Donacion donacion) {
        if (donacion.getMonto() <= 0) {
            throw new RuntimeException("El monto debe ser mayor a 0");
        }
        if (donacion.getIdUsuario() == null) {
            if (donacion.getMensaje() != null) {
                donacion.setMensaje("Donación anónima - " + donacion.getMensaje());
            } else {
                donacion.setMensaje("Donación anónima");
            }
        }
        donacion.setFecha(LocalDate.now());
        return donacionRepository.save(donacion);
    }


    public List<Donacion> findByIdUsuario(Long idUsuario) {
        return donacionRepository.findByIdUsuario(idUsuario);
    }

    public List<Donacion> findByMontoGreaterThanEqual(Double monto) {
        return donacionRepository.findByMontoGreaterThanEqual(monto);
    }




}
