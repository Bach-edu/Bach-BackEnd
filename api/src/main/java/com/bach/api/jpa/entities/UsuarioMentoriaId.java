package com.bach.api.jpa.entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

//clave primaria compuesta para tabla auxilear usario mentoria
@Embeddable
public class UsuarioMentoriaId implements Serializable {

    private Long mentoriaId;
    private  Long usuarioId;

    public UsuarioMentoriaId() {
    }

    public UsuarioMentoriaId(Long mentoriaId, Long usuarioId) {
        this.mentoriaId = mentoriaId;
        this.usuarioId  = usuarioId;
    }

    public Long getMentoriaId() {
        return mentoriaId;
    }

    public void setMentoriaId(Long mentoriaId) {
        this.mentoriaId = mentoriaId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioMentoriaId that)) return false;
        return Objects.equals(mentoriaId, that.mentoriaId) &&
                Objects.equals(usuarioId, that.usuarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mentoriaId, usuarioId);
    }
}
