package com.bach.api.jpa.entities;

import com.bach.api.api.types.DTOActualizacionCurso;
import com.bach.api.api.types.DTORegistroCurso;
import com.bach.api.jpa.enums.Instrumento;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "curso_musical")
public class CursoMusical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private Instrumento instrumentoBase;

    @OneToMany(mappedBy = "curso",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Mentoria> mentorias;

    public CursoMusical() {
        this.mentorias = new HashSet<>();
    }

    public CursoMusical(DTORegistroCurso datos) {
        this.titulo = datos.titulo();
        this.descripcion = datos.descripcion();
        this.instrumentoBase = datos.instrumentoBase();
        this.mentorias = new HashSet<>();
    }

    public Set<Mentoria> getMentorias() {
        return mentorias;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Instrumento getInstrumentoBase() {
        return instrumentoBase;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setInstrumentoBase(Instrumento instrumentoBase) {
        this.instrumentoBase = instrumentoBase;
    }

    public void actualizate(DTOActualizacionCurso datos) {
        if (datos.titulo() != null && !datos.titulo().isEmpty()) {
            this.titulo = datos.titulo();
        }
        if (datos.descripcion() != null && !datos.descripcion().isEmpty()) {
            this.descripcion = datos.descripcion();
        }
        if (datos.instrumentoBase() != null) {
            this.instrumentoBase = datos.instrumentoBase();
        }
    }
}
