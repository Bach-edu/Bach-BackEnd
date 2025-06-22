package com.bach.api.api.rests;

import com.bach.api.api.types.DatosJWT;
import com.bach.api.api.types.DtoAuteticacionUsuario;
import com.bach.api.config.security.TokenService;
import com.bach.api.jpa.entities.Usuario;
import com.bach.api.jpa.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;
        //autenticacion de usuarios
    @PostMapping
    public ResponseEntity logIn(@RequestBody @Valid DtoAuteticacionUsuario datosAutenticacion){
        //generamos auth token con datos de autenticacion
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacion.email(),datosAutenticacion.password());
        //verificamos que exista el usuario
        var usuarioAuth = authenticationManager.authenticate(authToken);
        //generamos el token final
        var token = tokenService.generarToken((Usuario) usuarioAuth.getPrincipal());
        return ResponseEntity.ok(new DatosJWT(token));
    }
}
