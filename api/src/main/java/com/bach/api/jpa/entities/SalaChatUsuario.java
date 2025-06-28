package com.bach.api.jpa.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "sala_chat_usuario")
public class SalaChatUsuario {

    @EmbeddedId
    private SalaChatUsuarioId id;

    @MapsId("salaChatId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sala_chat_id")
    private SalaChat salaChat;

    @MapsId("usuarioId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
