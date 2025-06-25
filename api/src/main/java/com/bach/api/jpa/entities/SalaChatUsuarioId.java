package com.bach.api.jpa.entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

//Clave primaria compuesta para SalaChatUsuario.

@Embeddable
public class SalaChatUsuarioId implements Serializable {

    private Long salaChatId;
    private Long usuarioId;

    public SalaChatUsuarioId() {}

    public SalaChatUsuarioId(Long salaChatId, Long usuarioId) {
        this.salaChatId = salaChatId;
        this.usuarioId = usuarioId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SalaChatUsuarioId that)) return false;
        return Objects.equals(salaChatId, that.salaChatId)
                && Objects.equals(usuarioId, that.usuarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salaChatId, usuarioId);
    }
}
