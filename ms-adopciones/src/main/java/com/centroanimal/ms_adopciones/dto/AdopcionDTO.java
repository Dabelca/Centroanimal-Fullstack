package com.centroanimal.ms_adopciones.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdopcionDTO {

    @NotNull(message = "El idUsuario es obligatorio")
    private Long idUsuario;

    @NotNull(message = "El idAnimal es obligatorio")
    private Long idAnimal;
}
