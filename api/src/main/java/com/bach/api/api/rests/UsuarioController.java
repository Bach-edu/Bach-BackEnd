package com.bach.api.api.rests;

import com.bach.api.api.types.DTOActualizacionUsuario;
import com.bach.api.api.types.DTORegistroUsuario;
import com.bach.api.api.types.DTORespuestaUsuario;
import com.bach.api.jpa.entities.Usuario;
import com.bach.api.jpa.enums.Role;
import com.bach.api.jpa.repositories.UsuarioRepository;
import com.bach.api.config.security.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    //registro de usuario no es necesario token
    @PostMapping("/registrar")
    public ResponseEntity<DTORespuestaUsuario> registraUsuario(@Valid @RequestBody DTORegistroUsuario datos){
        var pass = encoder.encode(datos.password());
        var usuario = new Usuario(datos, pass);
        repository.save(usuario);
        var datosRespuesta = new DTORespuestaUsuario(usuario);
        return ResponseEntity.ok(datosRespuesta);
    }

    //actualizar perfil a mentor
    @PutMapping("/registrar-mentor")
    @Transactional
    public ResponseEntity<DTORespuestaUsuario> registraMentor(@RequestHeader("Authorization") String token){
        var idUsuario = tokenService.getClaimId(token);
        System.out.println("el id de usuario es: "+idUsuario);
        var usuarioOptional = repository.findById(idUsuario);
        if (usuarioOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var usuario = usuarioOptional.get();
        usuario.actualizaRol();
        var datosRespuesta = new DTORespuestaUsuario(usuario);
        return ResponseEntity.ok(datosRespuesta);
    }

    //actualizar demas datos
    @PutMapping("/actualizar-usuario")
    @Transactional
    public ResponseEntity<DTORespuestaUsuario> actualizaUsuario(@RequestHeader("Authorization") String token,
                                                                @RequestBody DTOActualizacionUsuario datos){
        var usuarioId = tokenService.getClaimId(token);
        var usuarioOptional = repository.findById(usuarioId);
        if(usuarioOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var usuario = usuarioOptional.get();
        if (datos.password() != null && !datos.password().isEmpty()){
            var passEncrip = encoder.encode(datos.password());
            usuario.setPassEncode(passEncrip);
        }
        usuario.actualizate(datos);
        var datosRespuesta = new DTORespuestaUsuario(usuario);
        return ResponseEntity.ok(datosRespuesta);
    }

    //reactiva cuenta si se desactiva
    @PutMapping("/reactivar-cuenta")
    @Transactional
    public ResponseEntity<DTORespuestaUsuario> reactivarUsuario(@RequestHeader("Authorization") String token){
        var usuarioId = tokenService.getClaimId(token);
        var usuarioOptional = repository.findById(usuarioId);
        if (usuarioOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var usuario = usuarioOptional.get();
        usuario.setActivoTrue();
        var datos = new DTORespuestaUsuario(usuario);
        return ResponseEntity.ok(datos);
    }

    //obtiene solo tu informacion
    @GetMapping("/me")
    public ResponseEntity<DTORespuestaUsuario> consultaUsuario(@RequestHeader("Authorization") String token){
        var usuarioId = tokenService.getClaimId(token);
        var usuarioOptional = repository.findById(usuarioId);
        if (usuarioOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var usuario = usuarioOptional.get();
        var datosRespuesta = new DTORespuestaUsuario(usuario);
        return ResponseEntity.ok(datosRespuesta);
    }

    //lista de alumnos de mentor
    @GetMapping("/alumnos")
    public ResponseEntity<Page<DTORespuestaUsuario>> obtenAlumnos(@RequestHeader("Authorization") String token,
                                                                  Pageable pageable){
        var usuarioId = tokenService.getClaimId(token);
        var rol = tokenService.getClaimrol(token);
        if(!Objects.equals(rol, Role.MENTOR.toString())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        var alumnos = repository.findByMentorIdAndActivoTrue(usuarioId, pageable).map(DTORespuestaUsuario::new);
        if (alumnos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(alumnos);
    }

    //desactivar cuenta
    @DeleteMapping("/desactivar-cuenta")
    @Transactional
    public ResponseEntity desactivarUsuario(@RequestHeader("Authorization") String token){
        var usuarioId = tokenService.getClaimId(token);
        var usuarioOptional = repository.findById(usuarioId);
        if (usuarioOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var usuario = usuarioOptional.get();
        usuario.setActivoFalse();
        return ResponseEntity.ok().build();
    }

    //borrado total
    @DeleteMapping("/borrar-cuenta")
    public ResponseEntity borrarUsuario(@RequestHeader("Authorization") String token){
        var usuarioId = tokenService.getClaimId(token);
        var usuarioOptional = repository.findById(usuarioId);
        if (usuarioOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var usuario = usuarioOptional.get();
        repository.delete(usuario);
        return ResponseEntity.ok().build();
    }
}
