package com.bach.api.jpa.entities;

import com.bach.api.api.types.DTOActualizacionMentoria;
import com.bach.api.api.types.DTORegistroMentoria;
import jakarta.persistence.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "mentorias")
public class Mentoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mentor_id", nullable = false)
    private Usuario mentor;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private CursoMusical curso;

    private String tema;

    @ManyToMany
    @JoinTable(
            name = "mentoria_video",
            joinColumns = @JoinColumn(name = "mentoria_id"),
            inverseJoinColumns = @JoinColumn(name = "video_id")
    )
    private Set<Video> videos;

    @CurrentTimestamp
    private LocalDateTime fecha;

    public Mentoria(){}

    public Mentoria(DTORegistroMentoria datos, Usuario usuario, CursoMusical curso) {
        this.mentor = usuario;
        this.curso = curso;
        this.tema = datos.tema();
        this.videos = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public Usuario getMentor() {
        return mentor;
    }

    public CursoMusical getCurso() {
        return curso;
    }

    public String getTema() {
        return tema;
    }

    public Set<Video> getVideos() {
        return videos;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void actualiza(DTOActualizacionMentoria datos) {
        if (datos.tema() != null && datos.tema().isEmpty()){
            this.tema = datos.tema();
        }

    }
}
