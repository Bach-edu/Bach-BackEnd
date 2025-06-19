package com.bach.api.domain.model;

import com.bach.api.domain.model.usuario.Usuario;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "certificado")
@Table(name = "certificados")
public class Certificado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "curso_id",nullable = false)
    private CursoMusical curso;

    private String nombre;

    @CreationTimestamp
    private LocalDateTime fechaEmision;
}
