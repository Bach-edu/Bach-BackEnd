package com.bach.api.jpa.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "sala_chat")
@Table(name = "sala_chat")
public class SalaChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String tipo;

    @OneToMany(mappedBy = "salaChat")
    private Set<SalaChatUsuario> participantes;
}
