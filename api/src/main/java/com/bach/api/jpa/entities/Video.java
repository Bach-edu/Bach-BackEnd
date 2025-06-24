package com.bach.api.jpa.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String url;
    private String descripcion;
    private int duracion;

    @CurrentTimestamp
    private LocalDateTime fechaDeSubida;
}
