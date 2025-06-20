package com.bach.api.api.types;

import com.bach.api.jpa.enums.Instrumento;
import com.bach.api.jpa.enums.InteresMusical;
import com.bach.api.jpa.enums.Role;
import com.bach.api.jpa.entities.Usuario;

import java.util.Set;

public record DtoRespuestaUsuario(
         Long id,
         String username,
         String nombreReal,
         Role rol,
         Set<InteresMusical> intereses,
         Set<Instrumento> instrumentoDominados
) {
    public DtoRespuestaUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getUsername(), usuario.getNombreReal(),
                usuario.getRol(), usuario.getIntereses(), usuario.getInstrumentoDominados());
    }
}
