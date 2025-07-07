package com.bach.api.api.rests;

import com.bach.api.jpa.entities.CursoMusical;
import com.bach.api.jpa.enums.Role;
import com.bach.api.jpa.repositories.CursoRepository;
import com.bach.api.jpa.repositories.DesafioRepository;
import com.bach.api.jpa.repositories.MentoriaRepository;
import com.bach.api.jpa.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reportes")
public class ReportesController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private MentoriaRepository mentoriaRepository;

    @Autowired
    private DesafioRepository desafioRepository;

    @GetMapping("/usuarios-activos")
    public ResponseEntity<Map<Role, Long>> usuariosActivosPorRol() {
        List<Object[]> rows = usuarioRepository.countActiveUsersPerRoleRaw();
        Map<Role,Long> result = rows.stream()
                .collect(Collectors.toMap(
                        r -> (Role) r[0],
                        r -> (Long) r[1]
                ));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/mentorias-desafios-por-curso")
    public ResponseEntity<Map<String,Map<String, Long>>> metoriasYDesafiosPorCursos(){
        var stats = cursoRepository.findAll().stream()
                .collect(
                        Collectors.toMap(
                                CursoMusical::getTitulo, curso -> Map.of( "Mentorias ", mentoriaRepository.countByCursoId(curso.getId()),
                                        "Desafios ", desafioRepository.countByCursoId(curso.getId()))
                        )
                );
        return ResponseEntity.ok(stats);
    }


}
