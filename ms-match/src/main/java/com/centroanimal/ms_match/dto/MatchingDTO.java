package com.centroanimal.ms_match.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MatchingDTO {

    @NotNull(message = "El idUsuario es obligatorio")
    private Long idUsuario;

    private String especiePreferida;

    private String tamanoPreferido;

    private Integer edadPreferida;
}
