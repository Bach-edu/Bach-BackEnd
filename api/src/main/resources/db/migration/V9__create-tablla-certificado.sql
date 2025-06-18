CREATE TABLE certificado(
    id BIGINT NOT NULL AUTO_INCREMENT,
    usuario_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    fecha_emision TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (curso_id) REFERENCES curso_musical(id),
    PRIMARY KEY (id, usuario_id)
);