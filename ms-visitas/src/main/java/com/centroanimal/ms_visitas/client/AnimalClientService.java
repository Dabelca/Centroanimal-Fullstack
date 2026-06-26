package com.centroanimal.ms_visitas.client;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalClientService {

    private static final Logger log = LoggerFactory.getLogger(AnimalClientService.class);

    @Autowired
    private AnimalClient animalClient;

    public void verificarAnimalExiste(Long idAnimal) {
        try {
            animalClient.buscarPorId(idAnimal);
            log.info("Animal con id {} verificado exitosamente", idAnimal);
        } catch (FeignException.NotFound e) {
            log.warn("Animal no encontrado con id: {}", idAnimal);
            throw new RuntimeException("El animal con id " + idAnimal + " no existe.");
        } catch (FeignException e) {
            log.error("Error al comunicarse con ms-animales para id {}: {}", idAnimal, e.getMessage());
            throw new RuntimeException("Error al verificar el animal: servicio de animales no disponible.");
        }
    }
}