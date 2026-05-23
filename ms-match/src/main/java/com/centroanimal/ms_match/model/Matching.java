package com.centroanimal.ms_match.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "matchings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Matching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idUsuario;

    @Column(nullable = true)
    private String especiePreferida;

    @Column(nullable = true)
    private String tamanoPreferido;

    @Column(nullable = true)
    private Integer edadPreferida;

    @Column(nullable = false)
    private String resultado;


}

