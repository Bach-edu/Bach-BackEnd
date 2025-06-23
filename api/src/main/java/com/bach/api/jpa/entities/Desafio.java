package com.bach.api.jpa.entities;

import com.bach.api.jpa.enums.CategoriaDesafio;
import com.bach.api.jpa.enums.Dificultad;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "desafio")
@Table(name = "desafios")
public class Desafio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
