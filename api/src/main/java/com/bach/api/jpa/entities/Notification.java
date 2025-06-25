package com.bach.api.jpa.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "notificacion")
@Table(name = "notificaciones")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_destino_id", nullable = false)
    private Usuario usuario;

    private String tipo;
    private String contenido;
    private Boolean leido;

    @CreationTimestamp
    private LocalDateTime fechaEnvio;

    public Notification() {
    }

    public Notification(Usuario usuario, String tipo, String contenido) {
        this.usuario = usuario;
        this.tipo = tipo;
        this.contenido = contenido;
        this.leido = false;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public String getContenido() {
        return contenido;
    }

    public Boolean getLeido() {
        return leido;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void marcarLeido() {
        this.leido = true;
    }
}
