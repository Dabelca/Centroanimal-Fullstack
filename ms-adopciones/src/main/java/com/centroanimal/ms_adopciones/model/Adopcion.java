package com.centroanimal.ms_adopciones.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "adopcion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adopcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idUsuario;

    @Column(nullable = false)
    private Long idAnimal;

    @Column(nullable = false)
    private LocalDate fechaAdopcion;

    @Column(nullable = false)
    private String estado;

}