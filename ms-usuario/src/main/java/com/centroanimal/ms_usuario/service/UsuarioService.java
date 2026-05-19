package com.centroanimal.ms_usuario.service;

import com.centroanimal.ms_usuario.model.Usuario;
import com.centroanimal.ms_usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class UsuarioService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll(){
        log.info("Obteniendo todos los usuarios");
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        log.info("Buscando usuario por id {}" , id);
        return usuarioRepository.findById(id).get();
    }

    public Usuario save(Usuario usuario) {
        log.info("Guardando usuario");
        if (usuario.getId() == null) {
            if (usuarioRepository.existsByRut(usuario.getRut())) {
                log.warn("Rut ya existente: {}", usuario.getRut());
                throw new RuntimeException("Este RUT ya existe");
            }
            if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
                log.warn("Correo ya existente: {}", usuario.getCorreo());
                throw new RuntimeException("Este correo ya existe");
            }
        }
        return usuarioRepository.save(usuario);
    }

    public  void delete(Long id) {
        log.info("Eliminando usuario con id: {}", id);
        usuarioRepository.deleteById(id);
    }

    public Optional<Usuario> findByRut(String rut) {
        log.info("Buscando usuario por rut: {}", rut);
        return usuarioRepository.findByRut(rut);
    }

    public List<Usuario> findByRol(String rol) {
        log.info("Buscando usuarios por rol: {}", rol);
        return usuarioRepository.findByRol(rol);
    }

    public boolean existsByRut(String rut) {
        return usuarioRepository.existsByRut(rut);
    }

    public boolean existsByCorreo(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }

}
