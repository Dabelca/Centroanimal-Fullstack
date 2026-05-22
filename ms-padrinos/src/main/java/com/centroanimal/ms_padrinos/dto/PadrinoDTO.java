package com.centroanimal.ms_padrinos.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PadrinoDTO {

    @NotNull(message = "El idUsuario es obligatorio")
    private Long idUsuario;

    @NotNull(message = "El idAnimal es obligatorio")
    private Long idAnimal;

    @NotNull(message = "El monto de cuota es obligatorio")
    @Positive(message = "El monto de cuota debe ser mayor a 0")
    private Double montoCuota;
}
