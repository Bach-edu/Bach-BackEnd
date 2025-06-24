package com.bach.api.jpa.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario_mentoria")
public class UsuarioMentoria {

    @EmbeddedId
    private UsuarioMentoriaId id;

    @MapsId("mentoriaId")
    @ManyToOne
    @JoinColumn(name="mentoria_id", nullable = false)
    private Mentoria mentoria;

    @MapsId("usuarioId")
    @ManyToOne
    @JoinColumn(name="usuario_id", nullable = false)
    private Usuario usuario;

    public UsuarioMentoria(){}

    public UsuarioMentoria(Mentoria mentoria, Usuario usuario, UsuarioMentoriaId key) {
        this.id=key;
        this.mentoria = mentoria;
        this.usuario=usuario;
    }
}
