package com.centroanimal.ms_notificaciones.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NotificacionDTO {

    @NotNull(message = "El idUsuario es obligatorio")
    private Long idUsuario;

    @NotBlank(message = "El mensaje es obligatorio")
    private String mensaje;

    @NotBlank(message = "El tipo de notificacion es obligatorio")
    private String tipoNotificacion;
}