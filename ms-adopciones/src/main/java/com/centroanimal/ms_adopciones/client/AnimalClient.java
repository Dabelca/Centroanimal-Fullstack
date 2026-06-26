package com.centroanimal.ms_adopciones.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-animales", url = "${animal.service.url:http://localhost:9091}")
public interface AnimalClient {

    @GetMapping("/api/v1/animales/{id}")
    AnimalDTO buscarPorId(@PathVariable Long id);
}