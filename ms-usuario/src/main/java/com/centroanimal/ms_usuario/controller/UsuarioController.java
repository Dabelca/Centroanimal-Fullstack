package com.centroanimal.ms_usuario.controller;

import com.centroanimal.ms_usuario.dto.UsuarioDTO;
import com.centroanimal.ms_usuario.model.Usuario;
import com.centroanimal.ms_usuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = usuarioService.findAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<Usuario> crear(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setRut(usuarioDTO.getRut());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setDireccion(usuarioDTO.getDireccion());
        usuario.setClave(usuarioDTO.getClave());
        usuario.setRol(usuarioDTO.getRol());
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.findById(id);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuarioDTO) {
      try {
          Usuario usu = usuarioService.findById(id);
          usu.setNombre(usuarioDTO.getNombre());
          usu.setRut(usuarioDTO.getRut());
          usu.setCorreo(usuarioDTO.getCorreo());
          usu.setTelefono(usuarioDTO.getTelefono());
          usu.setDireccion(usuarioDTO.getDireccion());
          usu.setClave(usuarioDTO.getClave());
          usu.setRol(usuarioDTO.getRol());

          usuarioService.save(usu);
          return ResponseEntity.ok(usu);
      } catch (Exception e) {
          return  ResponseEntity.notFound().build();

      }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try {
            usuarioService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }



}
