CREATE TABLE usuario_instrumentos(
    usuario_id BIGINT NOT NULL,
    instrumento VARCHAR(18),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);