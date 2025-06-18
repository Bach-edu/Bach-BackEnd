CREATE TABLE chat_mensaje(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    sala_chat_id BIGINT NOT NULL,
    remitente_id BIGINT NOT NULL,
    contenido TEXT NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (sala_chat_id) REFERENCES sala_chat(id),
    FOREIGN KEY (remitente_id) REFERENCES usuarios(id)
);