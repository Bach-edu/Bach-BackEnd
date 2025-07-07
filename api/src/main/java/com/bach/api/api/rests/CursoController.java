package com.bach.api.api.rests;

import com.bach.api.api.types.DTOActualizacionCurso;
import com.bach.api.api.types.DTORegistroCurso;
//import com.bach.api.api.types.DTORespuestaCurso;
import com.bach.api.api.types.DTORespuestaCurso;
import com.bach.api.config.security.TokenService;
import com.bach.api.jpa.entities.CursoMusical;
import com.bach.api.jpa.entities.Notification;
import com.bach.api.jpa.entities.Usuario;
import com.bach.api.jpa.enums.Instrumento;
import com.bach.api.jpa.enums.Role;
import com.bach.api.jpa.repositories.CursoRepository;
import com.bach.api.jpa.repositories.NotificationRepository;
import com.bach.api.jpa.repositories.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/curso")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private TokenService tokenService;

    //aqui el mentor crea un curso
    @PostMapping("/registrar")
    public ResponseEntity<DTORespuestaCurso> registraCurso(@Valid @RequestBody DTORegistroCurso datos,
                                                           @RequestHeader("Authorization") String token){
        Role rolDeUsuario = Role.valueOf(tokenService.getClaimrol(token));
        if (rolDeUsuario != Role.ADMIN && rolDeUsuario != Role.MENTOR){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        var curso = new CursoMusical(datos);
        repository.save(curso);
        var datosRespuesta = new DTORespuestaCurso(curso);
        for (Usuario u : usuarioRepository.findAll()) {
            if (u.isActivo() && u.getInstrumentoDominados().contains(curso.getInstrumentoBase())) {
                Notification n = new Notification(u, "CURSO",
                        "Nuevo curso: " + curso.getTitulo());
                notificationRepository.save(n);
            }
        }
        return ResponseEntity.ok(datosRespuesta);
    }

    //aqui el mentor actualiza los cursos
    @PutMapping("/actualizar-curso/{idCurso}")
    @Transactional
    public ResponseEntity<DTORespuestaCurso> actualizaCurso(@PathVariable Long idCurso ,@RequestBody DTOActualizacionCurso datos,
                                                            @RequestHeader("Authorization") String token){
        Role rolDeUsuario = Role.valueOf(tokenService.getClaimrol(token));
        if (rolDeUsuario != Role.ADMIN && rolDeUsuario != Role.MENTOR){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        var cursoOpptional = repository.findById(idCurso);
        if (cursoOpptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var curso = cursoOpptional.get();
        curso.actualizate(datos);
        var datosRespuesta = new DTORespuestaCurso(curso);
        return ResponseEntity.ok(datosRespuesta);
    }

    //obteneos todos los cursos
    @GetMapping("/todos-cursos")
    public ResponseEntity<Page<DTORespuestaCurso>> obtenerCursos(Pageable pageable){
        var cursos = repository.findAll(pageable).map(DTORespuestaCurso::new);
        return ResponseEntity.ok(cursos);
    }

    //cursos filtrados por instrumento debemos mandar un parametro en la url
    @GetMapping("/cursos-por-instrumento")
    public ResponseEntity<Page<DTORespuestaCurso>> obtenerCursosPorIstrumento(Pageable pageable, @RequestParam String instrumentoBase){
        Instrumento instrumento = Instrumento.valueOf(instrumentoBase.toUpperCase());
        var cursos = repository.findByInstrumentoBase(instrumento, pageable).map(DTORespuestaCurso::new);
        return ResponseEntity.ok(cursos);
    }

    //borra el cirso que mandas en el id es un delete total
    @DeleteMapping("/borrar-curso/{idCurso}")
    public ResponseEntity borrarCurso(@PathVariable Long idCurso, @RequestHeader("Authorization") String token){
        Role rolDeUsuario = Role.valueOf(tokenService.getClaimrol(token));
        if (rolDeUsuario != Role.ADMIN && rolDeUsuario != Role.MENTOR){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        var cursoOpptional = repository.findById(idCurso);
        if (cursoOpptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var curso = cursoOpptional.get();
        repository.delete(curso);
        return ResponseEntity.ok().build();
    }
}
