package com.bach.api.api.types;


import com.bach.api.jpa.entities.Perfil;

public record DTORespuestaPerfil(
         Long id,
         String biografia,
         String experienciaMusical,
         String fotoUrl
) {
    public DTORespuestaPerfil(Perfil perfil) {
        this(perfil.getId(), perfil.getBiografia(),
                perfil.getExperienciaMusical(), perfil.getFotoUrl());
    }
}
