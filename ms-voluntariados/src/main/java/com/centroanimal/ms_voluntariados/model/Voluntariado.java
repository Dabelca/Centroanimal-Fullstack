package com.centroanimal.ms_voluntariados.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Table(name = "voluntariado")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voluntariado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idUsuario;

    @Column(nullable = false)
    private LocalDate fechaPostulacion;

    @Column(nullable = true)
    private LocalDate fechaInscripcion;

    @Column(nullable = false)
    private String horario;

    @Column(nullable = false)
    private String estado;



}
