package com.bach.api.jpa.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@Entity(name = "chat_mensaje")
@Table(name = "chat_mensaje")
public class ChatMensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sala_chat_id", nullable = false)
    private  SalaChat salaChat;

    @ManyToOne
    @JoinColumn(name = "remitente_id", nullable = false)
    private Usuario remitete;

    private String contenido;

    @CurrentTimestamp
    private LocalDateTime fecha;
}
