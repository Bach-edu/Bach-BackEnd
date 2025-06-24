//package com.bach.api.config.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
///**
// * Configuración de seguridad para la aplicación.
// * Habilita Web Security, define el filtro de seguridad, las reglas de acceso y los beans necesarios.
// */
//
//    @Configuration
//    @EnableWebSecurity
//    public class SecurityConfiguration {
//
//        @Autowired
//        private SecurityFilter securityFilter;
//
//    // Filtro personalizado que valida tokens JWT antes del procesamiento de autenticación
//
//    /**
//     * Configura la cadena de filtros de seguridad (SecurityFilterChain).
//     * - Desactiva CSRF (ya que usamos JWT y no sesiones)
//     * - Define política de sesión sin estado (STATELESS)
//     * - Permite acceso público a /login, /usuarios/registrar y rutas de Swagger
//     * - Requiere autenticación para el resto de endpoints
//     * - Añade el SecurityFilter antes del filtro de usuario/contraseña
//     */
//
//        @Bean
//        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//            return http
//                    // Configuración de CORS usando la configuración por defecto
//                    .cors(cors -> cors.configure(http))
//                    // Desactiva la protección CSRF
//                    .csrf(csrf -> csrf.disable())
//                    // Define que no se crearán sesiones HTTP
//                    .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                    // Define las reglas de autorización por ruta
//                    .authorizeHttpRequests(req -> {
//                        req.requestMatchers(HttpMethod.POST, "/login").permitAll()
//                                .requestMatchers(HttpMethod.POST, "/usuarios/registrar").permitAll()
//                                .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll();
//                        req.anyRequest().authenticated();
//                    })
//                    // Inserta el filtro de seguridad JWT antes del filtro de autenticación por formularios
//                    .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
//                    .build();
//        }
//
//    /**
//     * Provee el AuthenticationManager necesario para el proceso de autenticación.
//     * Se extrae de la configuración de autenticación de Spring.
//     */
//
//        @Bean
//        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//            return authenticationConfiguration.getAuthenticationManager();
//        }
//
//    /**
//     * Bean que provee encriptación de contraseñas usando BCrypt.
//     * Será utilizado por el servicio de usuarios para codificar y verificar contraseñas.
//     */
//
//        @Bean
//        public BCryptPasswordEncoder PasswordEncoder() {
//            return new BCryptPasswordEncoder();
//        }
//    }
//
package com.bach.api.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactiva protección CSRF (necesario para WebSockets sin token)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Permite todas las rutas sin autenticación
                );
        return http.build();
    }
}