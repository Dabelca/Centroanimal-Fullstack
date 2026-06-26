package com.centroanimal.ms_usuario.controller;

import com.centroanimal.ms_usuario.dto.UsuarioDTO;
import com.centroanimal.ms_usuario.model.Usuario;
import com.centroanimal.ms_usuario.service.UsuarioService;
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
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuarios", description = "Operaciones para la gestión de usuarios del centro")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Listar todos los usuarios", description = "Obtiene una lista de todos los usuarios registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente"),
            @ApiResponse(responseCode = "204", description = "No hay usuarios registrados")
    })
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = usuarioService.findAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo usuario", description = "Registra un nuevo usuario en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
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
    @Operation(summary = "Buscar usuario por ID", description = "Retorna un usuario según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.findById(id);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un usuario", description = "Actualiza los datos de un usuario existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
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
    @Operation(summary = "Eliminar un usuario", description = "Elimina un usuario del sistema por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try {
            usuarioService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }



}
