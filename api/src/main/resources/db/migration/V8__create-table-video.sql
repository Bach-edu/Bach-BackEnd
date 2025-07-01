CREATE TABLE video(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    uploader_id BIGINT NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    url VARCHAR(100) NOT NULL,
    descripcion TEXT,
    duracion INTEGER NOT NULL,
    fecha_de_subida TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (uploader_id) REFERENCES usuarios(id)
);