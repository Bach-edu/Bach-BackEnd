package com.bach.api.jpa.entities;

import com.bach.api.jpa.enums.Instrumento;
import jakarta.persistence.*;

@Entity(name = "curso_musical")
@Table(name = "curso_musical")
public class CursoMusical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private Instrumento instrumentoBase;
}
