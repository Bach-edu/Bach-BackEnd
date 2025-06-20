package com.bach.api.jpa.entities;

import jakarta.persistence.*;

@Entity(name = "perfil")
@Table(name = "perfiles")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    private String biografia;
    private String experienciaMusical;
    private String urlFoto;
}
