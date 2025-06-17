CREATE TABLE mentorias(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    mentor_id BIGINT NOT NULL,
    desafio_id BIGINT NOT NULL,
    tema VARCHAR(100) NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (mentor_id) REFERENCES usuarios(id),
    FOREIGN KEY (desafio_id) REFERENCES desafios(id)
);