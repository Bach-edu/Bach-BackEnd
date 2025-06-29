package com.bach.api.config.security;

import com.bach.api.jpa.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//servicio de autenticacion
@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    UsuarioRepository repository; // repositorio de usuarios

    //corroboramos usuario por email
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }
}