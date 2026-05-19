package com.centroanimal.ms_donaciones.controller;

import com.centroanimal.ms_donaciones.model.Donacion;
import com.centroanimal.ms_donaciones.service.DonacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/donaciones")
public class DonacionController {

    @Autowired
    private DonacionService donacionService;

    @GetMapping
    public ResponseEntity<List<Donacion>> listar() {
        List<Donacion> donaciones = donacionService.findAll();
        if (donaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(donaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donacion> buscarPorId(@PathVariable Long id) {
        try {
            Donacion donacion = donacionService.findById(id);
            return ResponseEntity.ok(donacion);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Donacion>> buscarPorUsuario(@PathVariable Long idUsuario) {
        List<Donacion> donaciones = donacionService.findByIdUsuario(idUsuario);
        if (donaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(donaciones);
    }

    @GetMapping("/monto/{monto}")
    public ResponseEntity<List<Donacion>> buscarPorMonto(@PathVariable Double monto) {
        List<Donacion> donaciones = donacionService.findByMontoGreaterThanEqual(monto);
        if (donaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(donaciones);
    }

    @PostMapping
    public ResponseEntity<Donacion> crear(@RequestBody Donacion donacion) {
        try {
            Donacion nuevaDonacion = donacionService.save(donacion);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaDonacion);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
