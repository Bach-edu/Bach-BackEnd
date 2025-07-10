package com.bach.api.api.events;

import com.bach.api.jpa.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AutenticacionExitosaLisentener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String username = ((UserDetails) event.getAuthentication().getPrincipal()).getUsername();
        usuarioRepository.findByUsernameOptional(username).ifPresent(usuario -> {
            usuario.resetearIntentos();
            usuarioRepository.save(usuario);
        });
    }
}
