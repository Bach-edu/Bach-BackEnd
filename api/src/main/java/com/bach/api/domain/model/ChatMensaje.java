package com.bach.api.domain.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@Entity(name = "chat_mensaje")
@Table(name = "chat_mensaje")
public class ChatMensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sala_chat_id", nullable = false)
    private  SalaChat salaChatId;

    @ManyToOne
    @JoinColumn(name = "remitente_id", nullable = false)
    private Usuario remiteteId;

    private String contenido;

    @CurrentTimestamp
    private LocalDateTime fecha;
}
