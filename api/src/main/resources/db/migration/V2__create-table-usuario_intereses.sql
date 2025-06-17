CREATE TABLE usuario_intereses(
    usuario_id BIGINT NOT NULL,
    interes VARCHAR(11) NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);