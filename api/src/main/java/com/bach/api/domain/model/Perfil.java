package com.bach.api.domain.model;

import jakarta.persistence.*;

@Entity(name = "perfil")
@Table(name = "perfiles")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    private String biografia;
    private String experienciaMusical;
    private String urlFoto;
}
