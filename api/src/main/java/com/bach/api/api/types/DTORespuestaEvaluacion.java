package com.bach.api.api.types;

import com.bach.api.jpa.entities.Evaluacion;

import java.time.LocalDateTime;

public record DTORespuestaEvaluacion(
         Long id,
        DTOResumenUsuario evaluador,
        DTORespuestaDesafio desafio,
        Long mentoriaId,
        double puntuacion,
        String comentario,
        LocalDateTime fecha
) {
    public DTORespuestaEvaluacion(Evaluacion evaluacion) {
        this(evaluacion.getId(), new DTOResumenUsuario(evaluacion.getEvaluador()),
                new DTORespuestaDesafio(evaluacion.getDesafio()),evaluacion.getMentoria().getId(),
                evaluacion.getPuntuacion(), evaluacion.getComentario(), evaluacion.getFecha());
    }
}
