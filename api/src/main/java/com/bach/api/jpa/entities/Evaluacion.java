package com.bach.api.jpa.entities;

import com.bach.api.api.types.DTOActualizacionEvaluacion;
import com.bach.api.api.types.DTORegistroEvaluacion;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "evaluaciones")
public class Evaluacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "evauador_id",nullable = false)
    private Usuario evaluador;

    @ManyToOne
    @JoinColumn(name = "desafio_id")
    private Desafio desafio;

    @ManyToOne @JoinColumn(name = "video_id")
    private Video video;

    @ManyToOne
    @JoinColumn(name = "mentoria_id")
    private Mentoria mentoria;

    private double puntuacion;
    private String comentario;

    @CreationTimestamp
    private LocalDateTime fecha;

    public Evaluacion(){}

    public Evaluacion(Usuario usuario, Desafio desafio, Video video ,DTORegistroEvaluacion datos) {
        this.evaluador = usuario;
        this.desafio=desafio;
        this.video = video;
        this.mentoria=desafio.getMentoria();
        this.puntuacion = datos.puntuacion();
        this.comentario = datos.comentario();
    }

    public Long getId() {
        return id;
    }

    public Usuario getEvaluador() {
        return evaluador;
    }

    public Desafio getDesafio() {
        return desafio;
    }

    public Video getVideo() {
        return video;
    }

    public Mentoria getMentoria() {
        return mentoria;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void actualiza(DTOActualizacionEvaluacion datos) {
        if(datos.puntuacion() >= 0){
            this.puntuacion=datos.puntuacion();
        }
        if(datos.comentario() != null && !datos.comentario().isEmpty()){
            this.comentario = datos.comentario();
        }
    }
}
