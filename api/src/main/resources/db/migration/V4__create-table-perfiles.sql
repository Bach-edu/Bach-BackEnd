CREATE TABLE perfiles(
    id BIGINT NOT NULL AUTO_INCREMENT,
    usuario_id BIGINT NOT NULL,
    biografia TEXT,
    experiencia_musical TEXT,
    foto_url VARCHAR(250),

    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    PRIMARY KEY (id, usuario_id)
);