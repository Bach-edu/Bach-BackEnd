package com.bach.api.domain.model;

import com.bach.api.domain.enums.Instrumento;
import jakarta.persistence.*;

@Entity(name = "curso_musical")
@Table(name = "curso_musical")
public class CursoMusical {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titulo;
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private Instrumento instrumentoBase;
}
