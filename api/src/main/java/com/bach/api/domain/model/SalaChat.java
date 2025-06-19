package com.bach.api.domain.model;

import jakarta.persistence.*;

@Entity(name = "sala_chat")
@Table(name = "sala_chat")
public class SalaChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String tipo;
}
