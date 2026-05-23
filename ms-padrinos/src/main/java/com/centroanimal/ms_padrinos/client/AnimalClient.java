package com.centroanimal.ms_padrinos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-animales", url = "http://localhost:8081")
public interface AnimalClient {

    @GetMapping("/api/v1/animales/{id}")
    Object buscarPorId(@PathVariable Long id);
}