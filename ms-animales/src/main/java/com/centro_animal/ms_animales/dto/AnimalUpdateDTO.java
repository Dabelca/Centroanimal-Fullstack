package com.centro_animal.ms_animales.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AnimalUpdateDTO extends AnimalDTO {

    private String descripcion;
}