package com.bach.api.jpa.entities;

import com.bach.api.api.types.DTOActualizacionVideo;
import com.bach.api.api.types.DTORegistroVideo;
import jakarta.persistence.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String url;
    private String descripcion;
    private int duracion;

    @ManyToMany(mappedBy = "videos")
    private Set<Mentoria> mentorias = new HashSet<>();

    @CurrentTimestamp
    private LocalDateTime fechaDeSubida;

    public  Video(){}

    public Video(DTORegistroVideo datos) {
        this.titulo = datos.titulo();
        this.url = datos.url();
        this.descripcion = datos.descripcion();
        this.duracion = datos.duracion();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getUrl() {
        return url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public Set<Mentoria> getMentorias() {
        return mentorias;
    }

    public LocalDateTime getFechaDeSubida() {
        return fechaDeSubida;
    }

    public void actualiza(DTOActualizacionVideo datos) {
        if (datos.titulo() != null && !datos.titulo().isEmpty()){
            this.titulo = datos.titulo();
        }
        if (datos.url() != null && !datos.url().isEmpty()){
            this.url = datos.url();
        }
        if (datos.descripcion() != null && !datos.descripcion().isEmpty()){
            this.descripcion = datos.descripcion();
        }
        if (this.duracion != datos.duracion() && datos.duracion() != 0){
            this.duracion = datos.duracion();
        }
    }
}
