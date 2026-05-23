package com.centroanimal.ms_reportes.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReporteDTO {

    @NotBlank(message = "El tipo de reporte es obligatorio")
    private String tipoReporte;

    @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;
}
