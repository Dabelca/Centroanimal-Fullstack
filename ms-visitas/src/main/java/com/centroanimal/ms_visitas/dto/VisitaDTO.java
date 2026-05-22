package com.centroanimal.ms_visitas.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VisitaDTO {

    private Long idUsuario;

    private Long idAnimal;

    @NotBlank(message = "El tipo de visita es obligatorio")
    private String tipoVisita;

    @NotNull(message = "La fecha de visita es obligatoria")
    @Future(message = "La fecha de visita debe ser una fecha futura")
    private LocalDate fechaVisita;

}
