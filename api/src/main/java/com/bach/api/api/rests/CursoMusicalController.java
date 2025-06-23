package com.bach.api.api.rests;

import com.bach.api.api.types.DTOActualizacionCurso;
import com.bach.api.config.security.TokenService;
import com.bach.api.jpa.entities.CursoMusical;
import com.bach.api.jpa.entities.Notification;
import com.bach.api.jpa.entities.Usuario;
import com.bach.api.jpa.repositories.CursoMusicalRepository;
import com.bach.api.jpa.repositories.NotificationRepository;
import com.bach.api.jpa.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
public class CursoMusicalController {

    @Autowired
    private CursoMusicalRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private TokenService tokenService;

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizar(@PathVariable Long id,
                                     @RequestBody DTOActualizacionCurso datos,
                                     @RequestHeader("Authorization") String token) {
        var cursoOpt = repository.findById(id);
        if (cursoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        CursoMusical curso = cursoOpt.get();
        curso.actualizate(datos);

        // enviar notificacion a todos los usuarios activos
        for (Usuario u : usuarioRepository.findAll()) {
            if (u.isActivo()) {
                Notification n = new Notification(u, "CURSO",
                        "Actualización del curso: " + curso.getTitulo());
                notificationRepository.save(n);
            }
        }
        return ResponseEntity.ok().build();
    }
}
