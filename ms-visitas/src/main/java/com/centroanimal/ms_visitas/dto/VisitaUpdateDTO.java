package com.centroanimal.ms_visitas.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class VisitaUpdateDTO extends VisitaDTO {

    @NotBlank(message = "El estado es obligatorio")
    private String estado;
}