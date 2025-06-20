package com.bach.api.api.rests;

import com.bach.api.api.types.DtoRegistroUsuario;
import com.bach.api.api.types.DtoRespuestaUsuario;
import com.bach.api.jpa.entities.Usuario;
import com.bach.api.jpa.repositories.UsuarioRepository;
import com.bach.api.config.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/registrar")
    public ResponseEntity<DtoRespuestaUsuario> registraUsuario(@Valid @RequestBody DtoRegistroUsuario datos){
        var pass = encoder.encode(datos.password());
        var usuario = new Usuario(datos, pass);
        repository.save(usuario);
        var datosRespuesta = new DtoRespuestaUsuario(usuario);
        return ResponseEntity.ok(datosRespuesta);
    }

    @PostMapping("/registrar-mentor")
    @Transactional
    public ResponseEntity<DtoRespuestaUsuario> registraMentor(@RequestHeader("Authorization") String token){
        var idUsuario = tokenService.getClaimId(token);
        var usuarioOptional = repository.findById(idUsuario);
        if (usuarioOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var usuario = usuarioOptional.get();
        usuario.actualizaRol();
        var datosRespuesta = new DtoRespuestaUsuario(usuario);
        return ResponseEntity.ok(datosRespuesta);
    }
}
