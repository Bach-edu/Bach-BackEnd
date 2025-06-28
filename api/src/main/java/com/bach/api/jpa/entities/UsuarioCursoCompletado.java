package com.bach.api.jpa.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario_curso_completado")
public class UsuarioCursoCompletado {
    @EmbeddedId
    private UsuarioCursoId id;

    @MapsId("usuarioId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @MapsId("cursoId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = false)
    private CursoMusical curso;

    @CurrentTimestamp
    private LocalDateTime fechaCompletado;
}
