package com.bach.api.jpa.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@Entity(name = "chat_mensaje")
@Table(name = "chat_mensaje")
public class ChatMensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sala_chat_id", nullable = false)
    private  SalaChat salaChatId;

    @ManyToOne
    @JoinColumn(name = "remitente_id", nullable = false)
    private Usuario remiteteId;

    private String contenido;

    @CurrentTimestamp
    private LocalDateTime fecha;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public SalaChat getSalaChatId() {
        return salaChatId;
    }
    public void setSalaChatId(SalaChat salaChatId) {
        this.salaChatId = salaChatId;
    }
    public Usuario getRemiteteId() {
        return remiteteId;
    }
    public void setRemiteteId(Usuario remiteteId) {
        this.remiteteId = remiteteId;
    }
    public String getContenido() {
        return contenido;
    }
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

}