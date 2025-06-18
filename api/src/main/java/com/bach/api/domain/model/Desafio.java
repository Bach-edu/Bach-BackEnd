package com.bach.api.domain.model;

import com.bach.api.domain.enums.CategoriaDesafio;
import com.bach.api.domain.enums.Dificultad;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "desafio")
@Table(name = "desafios")
public class Desafio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private CursoMusical curso;

    private String titulo;
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private CategoriaDesafio categoria;

    @Enumerated(EnumType.STRING)
    private Dificultad dificultad;

    private LocalDateTime fechaLimite;
}
