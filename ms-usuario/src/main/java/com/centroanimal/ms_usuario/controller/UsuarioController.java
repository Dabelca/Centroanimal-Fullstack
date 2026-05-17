package com.centroanimal.ms_usuario.controller;

import com.centroanimal.ms_usuario.model.Usuario;
import com.centroanimal.ms_usuario.service.UsuarioService;
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
    public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario) {
        Usuario usuarioNuevo = usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioNuevo);
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
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
      try {
          Usuario usu = usuarioService.findById(id);
          usu.setId(id);
          usu.setNombre(usuario.getNombre());
          usu.setRut(usuario.getRut());
          usu.setCorreo(usuario.getCorreo());
          usu.setTelefono(usuario.getTelefono());
          usu.setDireccion(usuario.getDireccion());
          usu.setClave(usuario.getClave());
          usu.setRol(usuario.getRol());

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
