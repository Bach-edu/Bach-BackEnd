package com.bach.api.api.types;

import com.bach.api.jpa.entities.Usuario;

public record DTOResumenUsuario(
        Long id,
        String username,
        String nombreReal
) {
    public DTOResumenUsuario(Usuario u) {
        this(u.getId(), u.getUsername(), u.getNombreReal());
    }
}