package com.centroanimal.ms_donaciones.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DonacionDTO {

    private Long idUsuario;

    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor a 0")
    private Double monto;

    private String mensaje;
}