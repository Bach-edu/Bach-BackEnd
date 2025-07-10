package com.bach.api.api.events;

import com.bach.api.jpa.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
public class FalloEnAutenticacionLisentener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    @Transactional
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        String email = (String) event.getAuthentication().getPrincipal();
        usuarioRepository.findByEmailOptional(email).ifPresent(usuario -> {
            usuario.incrementarIntentoFallido();
            if (usuario.getIntentosFallidos() >= 5) {
                usuario.bloquear();
            }
            usuarioRepository.save(usuario);
        });
    }
}
