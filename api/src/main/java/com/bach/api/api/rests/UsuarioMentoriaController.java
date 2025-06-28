package com.bach.api.api.rests;

import com.bach.api.api.types.DTORespuestaMentoria;
import com.bach.api.config.security.TokenService;
import com.bach.api.jpa.entities.UsuarioMentoria;
import com.bach.api.jpa.entities.UsuarioMentoriaId;
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
@RequestMapping("/mis-mentorias")
@SecurityRequirement(name = "bearer-key")
public class UsuarioMentoriaController {

    @Autowired
    private UsuarioMentoriaRepository repository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private MentoriaRepository mentoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioCursoRepository usuarioCursoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    //inscribirte en mentoria
    @PostMapping("/{idMentoria}/inscribir")
    public ResponseEntity inscribirEnMentoria(@PathVariable Long idMentoria, @RequestHeader("Authorization") String token){
        var usuarioId = tokenService.getClaimId(token);
        var usuarioOptional = usuarioRepository.findById(usuarioId);
        var mentoriaOptional = mentoriaRepository.findById(idMentoria);
        if (mentoriaOptional.isEmpty() || usuarioOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var key = new UsuarioMentoriaId(idMentoria,usuarioId);
        if (repository.existsById(key)){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        var usuarioMentoria = new UsuarioMentoria(mentoriaOptional.get(), usuarioOptional.get(), key);
        repository.save(usuarioMentoria);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //completar mentoria
    @PutMapping("/mentoria-completada/{idMentoria}")
    @Transactional
    public ResponseEntity<?> completarUnaMentoria(
            @PathVariable Long idMentoria,
            @RequestHeader("Authorization") String token
    ) {
        var usuarioId = tokenService.getClaimId(token);
        var key = new UsuarioMentoriaId(idMentoria, usuarioId);

        var usuarioMentoriaOptional = repository.findById(key);
        if (usuarioMentoriaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No estás inscrito en la mentoría " + idMentoria);
        }
        var usuarioMentoria = usuarioMentoriaOptional.get();
        usuarioMentoria.terminar();
        repository.save(usuarioMentoria);

        return ResponseEntity.ok("Mentoría " + idMentoria + " marcada como completada.");
    }

    //si todas las mentorias estan completadas completas el curso
    @PutMapping("/completar-curso/{idCurso}")
    @Transactional
    public ResponseEntity completarCurso(@PathVariable Long idCurso ,@RequestHeader("Authorization") String token){
        var usuarioId = tokenService.getClaimId(token);
        var cursoOptional = cursoRepository.findById(idCurso);
        var usuarioOptional = usuarioRepository.findById(usuarioId);

        if (cursoOptional.isEmpty() || usuarioOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var totalDeMentorias = mentoriaRepository.countByCursoId(idCurso);
        var completadas = repository.countByUsuarioIdAndMentoriaCursoIdAndCompletadaTrue(usuarioId,idCurso);
        if (totalDeMentorias == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no tiene mentorias aun");
        }
        if (completadas < totalDeMentorias){
            return ResponseEntity.badRequest().body("aun no");
        }
        return ResponseEntity.ok("finalizado");

    }

    //muestra tus mentorias inscritas
    @GetMapping("/inscritas")
    public ResponseEntity<Page<DTORespuestaMentoria>> obtenMentoriasIscritas(@RequestHeader("Authorization") String token,
                                                                             Pageable pageable){
        var usuarioId = tokenService.getClaimId(token);
        var mentorias = mentoriaRepository.findByUsuarioId(usuarioId,pageable).map(DTORespuestaMentoria::new);
        return ResponseEntity.ok(mentorias);
    }
}
