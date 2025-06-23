package com.bach.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Clase que representa una configuración personalizada cargada desde application.properties
 * Se utiliza para acceder a valores definidos bajo el prefijo "app.config".
 */

@Component
@ConfigurationProperties(prefix = "app.config")
public class SecretConfig {
    private String secret;

    //Setter del valor del secreto
    public void setSecret(String secret) {
        this.secret = secret;
    }

    //Getter del valor del proyecto
    public String getSecret() {
        return secret;
    }
}

