CREATE TABLE usuario_curso_completado (
  usuario_id       BIGINT NOT NULL,
  curso_id         BIGINT NOT NULL,
  fecha_completado TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY(usuario_id, curso_id),
  FOREIGN KEY(usuario_id) REFERENCES usuarios(id),
  FOREIGN KEY(curso_id)   REFERENCES curso_musical(id)
);