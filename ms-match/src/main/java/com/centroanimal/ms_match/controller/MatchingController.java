package com.centroanimal.ms_match.controller;

import com.centroanimal.ms_match.dto.MatchingDTO;
import com.centroanimal.ms_match.model.Matching;
import com.centroanimal.ms_match.service.MatchingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/matchings")
@Tag(name = "Matching", description = "Operaciones para la gestión de Matchings del centro")
public class MatchingController {

    @Autowired
    private MatchingService matchingService;

    @GetMapping
    @Operation(summary = "Listar todos los matchs", description = "Obtiene una lista de todos los matchs registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de matchs obtenidos exitosamente"),
            @ApiResponse(responseCode = "204", description = "No hay matches registrados")
    })
    public ResponseEntity<List<Matching>> buscarTodos() {
        return ResponseEntity.ok(matchingService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar match por ID", description = "Retorna un match según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "match encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "match no encontrado")
    })
    public ResponseEntity<Matching> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(matchingService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo match", description = "Registra un nuevo match en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Match creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Matching> crear(@Valid @RequestBody MatchingDTO matchingDTO) {
        Matching matching = new Matching();
        matching.setIdUsuario(matchingDTO.getIdUsuario());
        matching.setEspeciePreferida(matchingDTO.getEspeciePreferida());
        matching.setTamanoPreferido(matchingDTO.getTamanoPreferido());
        matching.setEdadPreferida(matchingDTO.getEdadPreferida());
        return ResponseEntity.status(HttpStatus.CREATED).body(matchingService.crear(matching));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un match", description = "Elimina unun match del sistema por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Match eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Match no encontrado")
    })
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        matchingService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{idUsuario}")
    @Operation(summary = "Buscar match por usuario", description = "Retorna un match según su usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "match encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "match no encontrado")
    })
    public ResponseEntity<List<Matching>> buscarPorUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(matchingService.findByIdUsuario(idUsuario));
    }

    @GetMapping("/especie/{especiePreferida}")
    @Operation(summary = "Buscar match por especie", description = "Retorna un match según su especie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "match encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "match no encontrado")
    })
    public ResponseEntity<List<Matching>> buscarPorEspecie(@PathVariable String especiePreferida) {
        return ResponseEntity.ok(matchingService.findByEspeciePreferida(especiePreferida));
    }

    @GetMapping("/tamano/{tamanoPreferido}")
    @Operation(summary = "Buscar match por tamaño", description = "Retorna un match según su tamaño")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "match encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "match no encontrado")
    })
    public ResponseEntity<List<Matching>> buscarPorTamano(@PathVariable String tamanoPreferido) {
        return ResponseEntity.ok(matchingService.findByTamanoPreferido(tamanoPreferido));
    }

    @GetMapping("/edad/{edadPreferida}")
    @Operation(summary = "Buscar match por edad", description = "Retorna un match según su edad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "match encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "match no encontrado")
    })
    public ResponseEntity<List<Matching>> buscarPorEdad(@PathVariable Integer edadPreferida) {
        return ResponseEntity.ok(matchingService.findByEdadPreferida(edadPreferida));
    }





}
