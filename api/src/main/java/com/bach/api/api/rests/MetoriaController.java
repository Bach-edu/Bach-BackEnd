/*package com.bach.api.api.rests;

import com.bach.api.api.types.DTOActualizacionMentoria;
import com.bach.api.api.types.DTORegistroMentoria;
import com.bach.api.api.types.DTORespuestaMentoria;
import com.bach.api.config.security.TokenService;
import com.bach.api.jpa.entities.Mentoria;
import com.bach.api.jpa.enums.Role;
import com.bach.api.jpa.repositories.CursoRepository;
import com.bach.api.jpa.repositories.MentoriaRepository;
import com.bach.api.jpa.repositories.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mentoria")
@SecurityRequirement(name = "bearer-key")
public class MetoriaController {

    @Autowired
    private MentoriaRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/crear-mentoria/{idCurso}")
    public ResponseEntity<DTORespuestaMentoria> creamentoria(@PathVariable Long idCurso ,@RequestBody DTORegistroMentoria datos,
                                                             @RequestHeader("Authorization") String token){
        var rolDeUsuario = Role.valueOf(tokenService.getClaimrol(token));
        if (rolDeUsuario != Role.ADMIN && rolDeUsuario != Role.MENTOR){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        var idUsuario = tokenService.getClaimId(token);
        var usuarioOptional = usuarioRepository.findById(idUsuario);
        if (usuarioOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var usuario = usuarioOptional.get();
        var cursoOptional = cursoRepository.findById(idCurso);

        if (cursoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var curso = cursoOptional.get();

        var mentoria = new Mentoria(datos, usuario, curso);
        repository.save(mentoria);
        var datosRetorno = new DTORespuestaMentoria(mentoria);
        return ResponseEntity.ok(datosRetorno);
    }

    @PutMapping("/actualizar/{idMentoria}")
    @Transactional
    public ResponseEntity<DTORespuestaMentoria> actualizaMentoria(@PathVariable Long idMentoria,
                                                                  @RequestHeader("Authorization") String token,@RequestBody DTOActualizacionMentoria datos){
        var rolDeUsuario = Role.valueOf(tokenService.getClaimrol(token));
        if (rolDeUsuario != Role.ADMIN && rolDeUsuario != Role.MENTOR){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        var mentoriaOptional = repository.findById(idMentoria);
        if (mentoriaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var mentoria = mentoriaOptional.get();
        mentoria.actualiza(datos);
        var datosRespuesta = new DTORespuestaMentoria(mentoria);
        return ResponseEntity.ok(datosRespuesta);
    }

    @GetMapping("/mentoria-por-mentor/{nombre}")
    public ResponseEntity<Page<DTORespuestaMentoria>> obtenMentoriasPorMentor(@PathVariable String nombre, Pageable pageable){
        var mentorias = repository.findByMentorNombreRealContainingIgnoreCase(nombre,pageable).map(DTORespuestaMentoria::new);
        if (mentorias.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(mentorias);
    }

    @GetMapping("/todas-mentorias")
    public ResponseEntity<Page<DTORespuestaMentoria>> obtenTodasMentorias(Pageable pageable){
        var mentorias = repository.findAll(pageable).map(DTORespuestaMentoria::new);
        if (mentorias.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(mentorias);
    }

    @GetMapping("/mentoria-por-curso/{idCurso}")
    public ResponseEntity<Page<DTORespuestaMentoria>> obtenMentoriasPorCurso(@PathVariable Long idCurso, Pageable pageable){
        var mentorias = repository.findByCursoId(idCurso,pageable).map(DTORespuestaMentoria::new);
        if (mentorias.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(mentorias);
    }

    @DeleteMapping("/{idMentoria}")
    public ResponseEntity borraMentoria(@PathVariable Long idMentoria, @RequestHeader("Authorization") String token){
        var rolDeUsuario = Role.valueOf(tokenService.getClaimrol(token));
        if (rolDeUsuario != Role.ADMIN && rolDeUsuario != Role.MENTOR){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        var mentoriaOptional = repository.findById(idMentoria);
        if (mentoriaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var mentoria = mentoriaOptional.get();
        repository.delete(mentoria);
        return ResponseEntity.ok().build();
    }
}
*/