CREATE TABLE sala_chat_usuario(
    sala_chat_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,

    PRIMARY KEY (sala_chat_id, usuario_id)
);