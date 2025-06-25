package com.bach.api.api.rests;

import com.bach.api.api.types.DTONotificacion;
import com.bach.api.config.security.TokenService;
import com.bach.api.jpa.entities.Notification;
import com.bach.api.jpa.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificaciones")
public class NotificationController {

    @Autowired
    private NotificationRepository repository;

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity<List<DTONotificacion>> obtener(@RequestHeader("Authorization") String token) {
        Long usuarioId = tokenService.getClaimId(token);
        var lista = repository.findByUsuarioIdOrderByFechaEnvioDesc(usuarioId)
                .stream().map(DTONotificacion::new).toList();
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}/leida")
    public ResponseEntity marcarLeida(@PathVariable Long id,
                                      @RequestHeader("Authorization") String token) {
        Long usuarioId = tokenService.getClaimId(token);
        var optional = repository.findById(id);
        if (optional.isEmpty() || !optional.get().getUsuario().getId().equals(usuarioId)) {
            return ResponseEntity.notFound().build();
        }
        Notification n = optional.get();
        n.marcarLeido();
        repository.save(n);
        return ResponseEntity.ok().build();
    }
}