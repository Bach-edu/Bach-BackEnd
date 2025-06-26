package com.bach.api.jpa.entities;

import com.bach.api.api.types.DTOActualizacionDesafio;
import com.bach.api.api.types.DTORegistroDesafio;
import com.bach.api.jpa.enums.CategoriaDesafio;
import com.bach.api.jpa.enums.Dificultad;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "desafios")
public class Desafio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private CursoMusical curso;

    @ManyToOne
    @JoinColumn(name = "mentoria_id", nullable = false)
    private Mentoria mentoria;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "desafio_video",
            joinColumns = @JoinColumn(name = "desafio_id"),
            inverseJoinColumns = @JoinColumn(name = "video_id")
    )
    private Set<Video> videos = new HashSet<>();

    private String titulo;
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private CategoriaDesafio categoria;

    @Enumerated(EnumType.STRING)
    private Dificultad dificultad;

    private LocalDateTime fechaLimite;

    public  Desafio(){}

    public Desafio(DTORegistroDesafio datos, Mentoria mentoria, CursoMusical curoso) {
        this.curso = curoso;
        this.mentoria = mentoria;
        this.titulo = datos.titulo();
        this.descripcion = datos.descripcion();
        this.categoria = datos.categoria();
        this.dificultad = datos.dificultad();
        this.fechaLimite = datos.fechaLimite();
    }

    public Long getId() {
        return id;
    }

    public CursoMusical getCurso() {
        return curso;
    }

    public Mentoria getMentoria() {
        return mentoria;
    }

    public Set<Video> getVideos() {
        return videos;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public CategoriaDesafio getCategoria() {
        return categoria;
    }

    public Dificultad getDificultad() {
        return dificultad;
    }

    public LocalDateTime getFechaLimite() {
        return fechaLimite;
    }

    public void actualiza(DTOActualizacionDesafio datos) {
        if (datos.titulo() != null && !datos.titulo().isEmpty()){
            this.titulo = datos.titulo();
        }
        if (datos.descripcion() != null && !datos.descripcion().isEmpty()){
            this.descripcion = datos.descripcion();
        }
        if (datos.dificultad() != null){
            this.dificultad = datos.dificultad();
        }
        if (datos.categoria() != null){
            this.categoria = datos.categoria();
        }
        if (datos.fechaLimite() != this.fechaLimite){
            this.fechaLimite = datos.fechaLimite();
        }
    }
}
