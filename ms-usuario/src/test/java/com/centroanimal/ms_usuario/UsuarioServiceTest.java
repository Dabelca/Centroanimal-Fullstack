package com.centroanimal.ms_usuario;

import com.centroanimal.ms_usuario.model.Usuario;
import com.centroanimal.ms_usuario.repository.UsuarioRepository;
import com.centroanimal.ms_usuario.service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Debe retornar lista de usuarios")
    void testFindAll() {
        Usuario usuario = new Usuario(1L, "Juan Pérez", "12345678-9", "juan@mail.com", "912345678", "Calle 1", "1234", "USER");
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        List<Usuario> resultado = usuarioService.findAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Juan Pérez", resultado.get(0).getNombre());
    }

    @Test
    @DisplayName("Debe retornar usuario por ID")
    void testFindById() {
        Usuario usuario = new Usuario(1L, "Juan Pérez", "12345678-9", "juan@mail.com", "912345678", "Calle 1", "1234", "USER");
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.findById(1L);

        assertNotNull(resultado);
        assertEquals("Juan Pérez", resultado.getNombre());
    }

    @Test
    @DisplayName("Debe guardar usuario correctamente")
    void testSave() {
        Usuario usuario = new Usuario(null, "Juan Pérez", "12345678-9", "juan@mail.com", "912345678", "Calle 1", "1234", "USER");
        Usuario usuarioGuardado = new Usuario(1L, "Juan Pérez", "12345678-9", "juan@mail.com", "912345678", "Calle 1", "1234", "USER");

        when(usuarioRepository.existsByRut("12345678-9")).thenReturn(false);
        when(usuarioRepository.existsByCorreo("juan@mail.com")).thenReturn(false);
        when(usuarioRepository.save(usuario)).thenReturn(usuarioGuardado);

        Usuario resultado = usuarioService.save(usuario);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    @DisplayName("Debe lanzar excepcion si RUT ya existe")
    void testSaveRutDuplicado() {
        Usuario usuario = new Usuario(null, "Juan Pérez", "12345678-9", "juan@mail.com", "912345678", "Calle 1", "1234", "USER");
        when(usuarioRepository.existsByRut("12345678-9")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> usuarioService.save(usuario));
    }

    @Test
    @DisplayName("Debe lanzar excepcion si correo ya existe")
    void testSaveCorreoDuplicado() {
        Usuario usuario = new Usuario(null, "Juan Pérez", "12345678-9", "juan@mail.com", "912345678", "Calle 1", "1234", "USER");
        when(usuarioRepository.existsByRut("12345678-9")).thenReturn(false);
        when(usuarioRepository.existsByCorreo("juan@mail.com")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> usuarioService.save(usuario));
    }

    @Test
    @DisplayName("Debe eliminar usuario por ID")
    void testDelete() {
        doNothing().when(usuarioRepository).deleteById(1L);

        usuarioService.delete(1L);

        verify(usuarioRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Debe retornar usuarios por rol")
    void testFindByRol() {
        Usuario usuario = new Usuario(1L, "Juan Pérez", "12345678-9", "juan@mail.com", "912345678", "Calle 1", "1234", "USER");
        when(usuarioRepository.findByRol("USER")).thenReturn(List.of(usuario));

        List<Usuario> resultado = usuarioService.findByRol("USER");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("USER", resultado.get(0).getRol());
    }
}