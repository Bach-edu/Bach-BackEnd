package com.bach.api.api.rests;

import com.bach.api.api.types.DTOActualizacionPerfil;
import com.bach.api.api.types.DTORespuestaPerfil;
import com.bach.api.config.security.TokenService;
import com.bach.api.jpa.repositories.PerfilRepository;
import com.bach.api.jpa.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perfiles")
public class PerfilController {

    @Autowired
    private PerfilRepository repository;

    @Autowired
    private TokenService tokenService;


    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DTORespuestaPerfil> actualizaPerfil(@RequestBody DTOActualizacionPerfil datos,
                                                              @RequestHeader("Authorization") String token){
        var usuarioId = tokenService.getClaimId(token);
        var perfilOptional = repository.findByUsuarioId(usuarioId);
        if (perfilOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var perfil = perfilOptional.get();
        perfil.actualizate(datos);
        var datosRespuesta = new DTORespuestaPerfil(perfil);
        return ResponseEntity.ok(datosRespuesta);
    }

    @GetMapping("/obtener-perfil")
    public ResponseEntity<DTORespuestaPerfil> obtienePerfil(@RequestHeader("Authorization") String token){
        var usuarioId = tokenService.getClaimId(token);
        var perfilOptional = repository.findByUsuarioId(usuarioId);
        if (perfilOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var perfil = perfilOptional.get();
        var datosRespuesta = new DTORespuestaPerfil(perfil);
        return ResponseEntity.ok(datosRespuesta);
    }
}
