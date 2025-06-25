package com.bach.api.jpa.entities;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class UsuarioCursoId {

    private Long usuarioId;
    private Long cursoId;

    public UsuarioCursoId(){}

    public UsuarioCursoId(Long usuarioId, Long cursoId){
        this.cursoId = cursoId;
        this.usuarioId = usuarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioCursoId that)) return false;
        return Objects.equals(cursoId, that.cursoId) &&
                Objects.equals(usuarioId, that.usuarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cursoId, usuarioId);
    }
}
