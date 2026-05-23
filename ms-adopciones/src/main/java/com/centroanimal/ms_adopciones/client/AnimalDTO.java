package com.centroanimal.ms_adopciones.client;

import lombok.Data;

@Data
public class AnimalDTO {
    private Long id;
    private String nombre;
    private String especie;
    private String raza;
    private Integer edad;
    private String estado;
}