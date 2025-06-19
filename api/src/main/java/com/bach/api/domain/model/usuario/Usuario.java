package com.bach.api.domain.model.usuario;

import com.bach.api.domain.enums.Instrumento;
import com.bach.api.domain.enums.InteresMusical;
import com.bach.api.domain.enums.Role;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity(name = "usuario")
@Table(name = "usuarios")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public  Usuario(){}

    public Usuario(DtoRegistroUsuario datos, String passwordHash) {
        this.username = datos.username();
        this.nombreReal = datos.nombreReal();
        this.email = datos.email();
        this.passwordHash = passwordHash;
        this.rol = Role.USUARIO;
        this.intereses = datos.intereses();
        this.instrumentoDominados = datos.instrumentoDominados();
    }


    public Long getId() {
        return id;
    }

    public String getNombreReal() {
        return nombreReal;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Role getRol() {
        return rol;
    }

    public Set<InteresMusical> getIntereses() {
        return intereses;
    }

    public Set<Instrumento> getInstrumentoDominados() {
        return instrumentoDominados;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+this.rol.name()));
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void actualizaRol() {
        this.rol=Role.MENTOR;
    }
}
