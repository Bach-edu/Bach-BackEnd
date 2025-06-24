CREATE TABLE mentorias(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    mentor_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,
    tema VARCHAR(100) NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (mentor_id) REFERENCES usuarios(id),
    FOREIGN KEY (curso_id) REFERENCES curso_musical(id)
);