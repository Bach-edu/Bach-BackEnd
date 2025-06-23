package com.bach.api.jpa.entities;

import com.bach.api.api.types.DTOActualizacionPerfil;
import jakarta.persistence.*;

@Entity(name = "perfil")
@Table(name = "perfiles")
public class Perfil {
    @Id
    private Long id;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    private String biografia;
    private String experienciaMusical;
    private String fotoUrl;

    public Perfil(){}

    public Perfil(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getBiografia() {
        return biografia;
    }

    public String getExperienciaMusical() {
        return experienciaMusical;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void actualizate(DTOActualizacionPerfil datos) {
        if(datos.biografia() != null && !datos.biografia().isEmpty()){
            this.biografia = datos.biografia();
        }if(datos.experienciaMusical() != null && !datos.experienciaMusical().isEmpty()){
            this.experienciaMusical = datos.experienciaMusical();
        }if(datos.fotoUrl() != null && !datos.fotoUrl().isEmpty()){
            this.fotoUrl = datos.fotoUrl();
        }
    }
}
