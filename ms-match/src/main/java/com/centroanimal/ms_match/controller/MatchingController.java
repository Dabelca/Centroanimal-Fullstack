package com.centroanimal.ms_match.controller;

import com.centroanimal.ms_match.dto.MatchingDTO;
import com.centroanimal.ms_match.model.Matching;
import com.centroanimal.ms_match.service.MatchingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/matchings")
public class MatchingController {

    @Autowired
    private MatchingService matchingService;

    @GetMapping
    public ResponseEntity<List<Matching>> buscarTodos() {
        return ResponseEntity.ok(matchingService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matching> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(matchingService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Matching> crear(@Valid @RequestBody MatchingDTO matchingDTO) {
        Matching matching = new Matching();
        matching.setIdUsuario(matchingDTO.getIdUsuario());
        matching.setEspeciePreferida(matchingDTO.getEspeciePreferida());
        matching.setTamanoPreferido(matchingDTO.getTamanoPreferido());
        matching.setEdadPreferida(matchingDTO.getEdadPreferida());
        return ResponseEntity.status(HttpStatus.CREATED).body(matchingService.crear(matching));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        matchingService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Matching>> buscarPorUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(matchingService.findByIdUsuario(idUsuario));
    }

    @GetMapping("/especie/{especiePreferida}")
    public ResponseEntity<List<Matching>> buscarPorEspecie(@PathVariable String especiePreferida) {
        return ResponseEntity.ok(matchingService.findByEspeciePreferida(especiePreferida));
    }

    @GetMapping("/tamano/{tamanoPreferido}")
    public ResponseEntity<List<Matching>> buscarPorTamano(@PathVariable String tamanoPreferido) {
        return ResponseEntity.ok(matchingService.findByTamanoPreferido(tamanoPreferido));
    }

    @GetMapping("/edad/{edadPreferida}")
    public ResponseEntity<List<Matching>> buscarPorEdad(@PathVariable Integer edadPreferida) {
        return ResponseEntity.ok(matchingService.findByEdadPreferida(edadPreferida));
    }





}
