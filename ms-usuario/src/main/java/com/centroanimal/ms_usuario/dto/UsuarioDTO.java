package com.centroanimal.ms_usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El rut es obligatorio")
    @Size(max = 12, message = "El rut no puede tener mas de 12 caracteres")
    private String rut;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no es valido")
    private String correo;

    @NotBlank(message = "El telefono es obligatorio")
    @Size(max = 11, message = "El telefono no puede tener mas de 11 caracteres")
    private String telefono;

    @NotBlank(message = "La direccion es obligatoria")
    private String direccion;

    @NotBlank(message = "La clave es obligatoria")
    private String clave;

    @NotBlank(message = "El rol es obligatorio")
    private String rol;
}
