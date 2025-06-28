CREATE TABLE curso_musical(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL UNIQUE,
    descripcion TEXT,
    instrumento_BASE VARCHAR(18)
);