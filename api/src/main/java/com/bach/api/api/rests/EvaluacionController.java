package com.bach.api.api.rests;

import com.bach.api.api.types.DTOActualizacionEvaluacion;
import com.bach.api.api.types.DTORegistroEvaluacion;
import com.bach.api.api.types.DTORespuestaEvaluacion;
import com.bach.api.config.security.TokenService;
import com.bach.api.jpa.entities.Evaluacion;
import com.bach.api.jpa.entities.Notification;
import com.bach.api.jpa.entities.Usuario;
import com.bach.api.jpa.enums.Role;
import com.bach.api.jpa.repositories.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evaluaciones")
@SecurityRequirement(name = "bearer-key")
public class EvaluacionController {

    @Autowired
    private EvaluacionRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DesafioRepository desafioRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/crear-evaluacion/desafio/{desafioId}/video/{videoId}")
    public ResponseEntity<DTORespuestaEvaluacion> crearEvaluacion(@PathVariable Long desafioId, @PathVariable Long videoId ,@RequestBody DTORegistroEvaluacion datos,
                                                                  @RequestHeader("Authorization") String token){
        Role rolDeUsuario = Role.valueOf(tokenService.getClaimrol(token));
        if (rolDeUsuario != Role.ADMIN && rolDeUsuario != Role.MENTOR){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        var usuarioId = tokenService.getClaimId(token);
        var usuarioOptional = usuarioRepository.findById(usuarioId);
        var desafioOptional = desafioRepository.findById(desafioId);
        if (usuarioOptional.isEmpty() || desafioOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var usuario = usuarioOptional.get();
        var desafio = desafioOptional.get();
        var videoOptional = videoRepository.findById(videoId);
        if (videoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var video = videoOptional.get();
        var evaluacion = new Evaluacion(usuario, desafio, video ,datos);
        repository.save(evaluacion);
        var datosRespuesta = new DTORespuestaEvaluacion(evaluacion);
        for (Usuario u : usuarioRepository.findAll()) {
            if (u.isActivo() && evaluacion.getVideo().getUploader() == u) {
                Notification n = new Notification(u, "EVALUACION",
                        "Nueva evaluacion de mi video: " + evaluacion.getVideo().getTitulo());
                notificationRepository.save(n);
            }
        }
        return ResponseEntity.ok(datosRespuesta);
    }

    @PutMapping("/editar-evaluacion/{idEvaluacion}")
    @Transactional
    public ResponseEntity<DTORespuestaEvaluacion> editaEvaluacion(@PathVariable Long idEvaluacion,
                                                                  @RequestBody DTOActualizacionEvaluacion datos,
                                                                  @RequestHeader("Authorization") String token){
        Role rolDeUsuario = Role.valueOf(tokenService.getClaimrol(token));
        if (rolDeUsuario != Role.ADMIN && rolDeUsuario != Role.MENTOR){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        var evaluacionOpcional = repository.findById(idEvaluacion);
        if (evaluacionOpcional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var evaluacion = evaluacionOpcional.get();
        evaluacion.actualiza(datos);
        var datosRespuesta = new DTORespuestaEvaluacion(evaluacion);
        return ResponseEntity.ok(datosRespuesta);
    }

    //evaluaciones de mentores
    @GetMapping("/evaluaciones-hechas")
    public ResponseEntity<Page<DTORespuestaEvaluacion>> muestraEvaluacionesDelMentor(@RequestHeader("Authorization") String token,
                                                                                     Pageable pageable){
        Role rolDeUsuario = Role.valueOf(tokenService.getClaimrol(token));
        if (rolDeUsuario != Role.ADMIN && rolDeUsuario != Role.MENTOR){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        var evaluadorId = tokenService.getClaimId(token);
        var evaluaciones = repository.findByEvaluadorId(evaluadorId, pageable).map(DTORespuestaEvaluacion::new);
        return ResponseEntity.ok(evaluaciones);
    }

    //evaluaciones del Usuario
    @GetMapping("/mis-evaluaciones")
    public ResponseEntity<Page<DTORespuestaEvaluacion>> muestraEvaluacionesDelUsuario(@RequestHeader("Authorization") String token,
                                                                                     Pageable pageable){
        var usuarioId = tokenService.getClaimId(token);
        var evaluaciones = repository.findByVideoUploaderId(usuarioId, pageable).map(DTORespuestaEvaluacion::new);
        return ResponseEntity.ok(evaluaciones);
    }

    @DeleteMapping("/{idEvaluacion}")
    public ResponseEntity borraEvaluacion(@PathVariable Long idEvaluacion, @RequestHeader("Authorization") String token){
        Role rolDeUsuario = Role.valueOf(tokenService.getClaimrol(token));
        if (rolDeUsuario != Role.ADMIN && rolDeUsuario != Role.MENTOR){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        var evaluacionOptional = repository.findById(idEvaluacion);
        if (evaluacionOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var evaluacion = evaluacionOptional.get();
        repository.delete(evaluacion);
        return ResponseEntity.ok("Evaluacion Eliminada");
    }
}
