package com.bach.api.jpa.entities;

import com.bach.api.jpa.enums.Instrumento;
import com.bach.api.jpa.enums.InteresMusical;
import com.bach.api.jpa.enums.Role;
import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "usuario")
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private  String nombreReal;
    private String email;
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private Role rol;

    @ElementCollection(targetClass = InteresMusical.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "usuario_intereses", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "interes")
    Set<InteresMusical> intereses;

    @ElementCollection(targetClass = Instrumento.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "usuario_instrumentos", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "instrumento")
    Set<Instrumento> instrumentoDominados;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getNombreReal() {
        return nombreReal;
    }
    public void setNombreReal(String nombreReal) {
        this.nombreReal = nombreReal;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    public Role getRol() {
        return rol;
    }
    public void setRol(Role rol) {
        this.rol = rol;
    }
}