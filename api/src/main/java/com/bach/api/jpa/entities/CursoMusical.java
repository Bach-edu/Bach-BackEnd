package com.bach.api.jpa.entities;

import com.bach.api.api.types.DTOActualizacionCurso;
import com.bach.api.api.types.DTORegistroCurso;
import com.bach.api.jpa.enums.Instrumento;
import jakarta.persistence.*;

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

    public CursoMusical(){}

    public CursoMusical(DTORegistroCurso datos) {
        this.titulo = datos.titulo();
        this.descripcion = datos.descripcion();
        this.instrumentoBase = datos.instrumentoBase();
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

    public void actualizate(DTOActualizacionCurso datos) {
        if (datos.titulo() != null && !datos.titulo().isEmpty()){
            this.titulo = datos.titulo();
        }
        if (datos.descripcion() != null && !datos.descripcion().isEmpty()){
                this.descripcion = datos.descripcion();
        }
        if (datos.instrumentoBase() != null){
            this.instrumentoBase = datos.instrumentoBase();
        }
    }
}
