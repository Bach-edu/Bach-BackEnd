package com.bach.api.jpa.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "evaluacion")
@Table(name = "evaluaciones")
public class Evaluacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "evaluador_id",nullable = false)
    private Usuario evaluador;

    @ManyToOne
    @JoinColumn(name = "desafio_id")
    private Desafio desafio;

    @ManyToOne
    @JoinColumn(name = "mentoria_id")
    private Mentoria mentoria;

    private double puntuacion;
    private String comentario;

    @CreationTimestamp
    private LocalDateTime fecha;
}
