package com.centroanimal.ms_visitas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "visitas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private Long idUsuario;

    @Column(nullable = true)
    private Long idAnimal;

    @Column(nullable = false)
    private String tipoVisita;

    @Column(nullable = false)
    private LocalDate fechaVisita;

    @Column(nullable = false)
    private String estado;
}
