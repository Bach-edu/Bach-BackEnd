package com.bach.api.domain.model;

import com.bach.api.domain.enums.Instrumento;
import com.bach.api.domain.enums.InteresMusical;
import com.bach.api.domain.enums.Role;

import java.util.Set;

public class Usuario {
    private Long id;
    private String username;
    private  String nombreReal;
    private String email;
    private String passwordHash;
    private Role rol;
    Set<InteresMusical> intereses;
    Set<Instrumento> instrumentoDominados;
}
