package com.bach.api.domain.model.usuario;

import com.bach.api.domain.enums.Instrumento;
import com.bach.api.domain.enums.InteresMusical;
import com.bach.api.domain.enums.Role;

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
