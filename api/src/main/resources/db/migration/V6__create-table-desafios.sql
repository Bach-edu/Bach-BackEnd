CREATE TABLE desafios(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    curso_id BIGINT NOT NULL,
    titulo VARCHAR(100) NOT NULL UNIQUE,
    descripcion TEXT,
    categoria VARCHAR(11) NOT NULL,
    dificultad VARCHAR(10) NOT NULL,
    fecha_limite DATE NOT NULL,

   FOREIGN KEY (curso_id) REFERENCES curso_musical(id)
);