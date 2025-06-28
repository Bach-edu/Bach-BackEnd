CREATE TABLE notificaciones(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    usuario_destino_id BIGINT NOT NULL,
    tipo VARCHAR(250) NOT NULL,
    contenido VARCHAR(250) NOT NULL,
    leido BOOLEAN DEFAULT FALSE,
    fecha_envio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (usuario_destino_id) REFERENCES usuarios(id)
);