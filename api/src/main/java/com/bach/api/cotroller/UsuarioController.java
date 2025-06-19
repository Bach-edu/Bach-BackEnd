package com.bach.api.cotroller;

import com.bach.api.domain.model.usuario.DtoRegistroUsuario;
import com.bach.api.domain.model.usuario.DtoRespuestaUsuario;
import com.bach.api.domain.model.usuario.Usuario;
import com.bach.api.domain.model.usuario.UsuarioRepository;
import com.bach.api.infra.security.TokenService;
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
        var idUsuario = tokenService.getClaim("id");
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
