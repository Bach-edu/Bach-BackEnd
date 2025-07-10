CREATE TABLE usuario_mentoria(
    mentoria_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    completada BOOLEAN NOT NULL DEFAULT FALSE,

    PRIMARY KEY (mentoria_id, usuario_id),
    FOREIGN KEY (mentoria_id) REFERENCES mentorias(id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);