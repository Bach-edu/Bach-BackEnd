package com.bach.api.domain.model;

import com.bach.api.domain.enums.Instrumento;
import com.bach.api.domain.enums.InteresMusical;
import com.bach.api.domain.enums.Role;
import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "usuario")
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private  String nombreReal;
    private String email;
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private Role rol;

    @ElementCollection(targetClass = InteresMusical.class)
            @Enumerated(EnumType.STRING)
            @CollectionTable(name = "usuario_intereses", joinColumns = @JoinColumn(name = "usuario_id"))
            @Column(name = "interes")
    Set<InteresMusical> intereses;

    @ElementCollection(targetClass = Instrumento.class)
            @Enumerated(EnumType.STRING)
            @CollectionTable(name = "usuario_instrumentos", joinColumns = @JoinColumn(name = "usuario_id"))
            @Column(name = "instrumento")
    Set<Instrumento> instrumentoDominados;
}
