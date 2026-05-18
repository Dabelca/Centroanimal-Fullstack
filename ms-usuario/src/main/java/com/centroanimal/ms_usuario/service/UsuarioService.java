package com.centroanimal.ms_usuario.service;

import com.centroanimal.ms_usuario.model.Usuario;
import com.centroanimal.ms_usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).get();
    }

    public Usuario save(Usuario usuario) {
        if (usuario.getId() == null) {
            if (usuarioRepository.existsByRut(usuario.getRut())) {
                throw new RuntimeException("Este RUT ya existe");
            }
            if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
                throw new RuntimeException("Este correo ya existe");
            }
        }
        return usuarioRepository.save(usuario);
    }

    public  void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Optional<Usuario> findByRut(String rut) {
        return usuarioRepository.findByRut(rut);
    }

    public List<Usuario> findByRol(String rol) {
        return usuarioRepository.findByRol(rol);
    }

    public boolean existsByRut(String rut) {
        return usuarioRepository.existsByRut(rut);
    }

    public boolean existsByCorreo(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }

}
