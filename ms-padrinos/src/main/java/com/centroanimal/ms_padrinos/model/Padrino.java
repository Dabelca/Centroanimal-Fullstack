package com.centroanimal.ms_padrinos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "padrinos")
public class Padrino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idUsuario;

    @Column(nullable = false)
    private Long idAnimal;

    @Column(nullable = false)
    private Double montoCuota;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private String estado;

}
