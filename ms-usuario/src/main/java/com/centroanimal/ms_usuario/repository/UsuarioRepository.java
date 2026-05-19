package com.centroanimal.ms_usuario.repository;

import com.centroanimal.ms_usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByRut(String rut);

    List<Usuario> findByRol(String rol);

    boolean existsByRut(String rut);

    boolean existsByCorreo(String correo);


}
