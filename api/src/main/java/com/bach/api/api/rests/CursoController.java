package com.bach.api.api.rests;

import com.bach.api.api.types.DTOActualizacionCurso;
import com.bach.api.api.types.DTORegistroCurso;
import com.bach.api.api.types.DTORespuestaCurso;
import com.bach.api.config.security.TokenService;
import com.bach.api.jpa.entities.CursoMusical;
import com.bach.api.jpa.enums.Instrumento;
import com.bach.api.jpa.enums.Role;
import com.bach.api.jpa.repositories.CursoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
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
    private TokenService tokenService;

    @PostMapping("/registrar")
    public ResponseEntity<DTORespuestaCurso> registraCurso(@RequestBody DTORegistroCurso datos,
                                                           @RequestHeader("Authorization") String token){
        Role rolDeUsuario = Role.valueOf(tokenService.getClaimrol(token));
        if (rolDeUsuario != Role.ADMIN && rolDeUsuario != Role.MENTOR){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        var curso = new CursoMusical(datos);
        repository.save(curso);
        var datosRespuesta = new DTORespuestaCurso(curso);
        return ResponseEntity.ok(datosRespuesta);
    }

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

    @GetMapping("/todos-cursos")
    public ResponseEntity<Page<DTORespuestaCurso>> obtenerCursos(Pageable pageable){
        var cursos = repository.findAll(pageable).map(DTORespuestaCurso::new);
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/cursos-por-instrumento")
    public ResponseEntity<Page<DTORespuestaCurso>> obtenerCursosPorIstrumento(Pageable pageable, @RequestParam String instrumentoBase){
        Instrumento instrumento = Instrumento.valueOf(instrumentoBase.toUpperCase());
        var cursos = repository.findByInstrumentoBase(instrumento, pageable).map(DTORespuestaCurso::new);
        return ResponseEntity.ok(cursos);
    }

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
