package com.bach.api.jpa.entities;

import com.bach.api.api.types.DTOActualizacionUsuario;
import com.bach.api.api.types.DTORegistroUsuario;
import com.bach.api.jpa.enums.Instrumento;
import com.bach.api.jpa.enums.InteresMusical;
import com.bach.api.jpa.enums.Role;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
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

    @OneToMany(mappedBy = "usuario")
    private Set<SalaChatUsuario> salasChat;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioMentoria> mentorias;

    @OneToOne(mappedBy = "usuario",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Perfil perfil = new Perfil(this);

    private boolean activo;

    public  Usuario(){}

    public Usuario(DTORegistroUsuario datos, String passwordHash) {
        this.username = datos.username();
        this.nombreReal = datos.nombreReal();
        this.email = datos.email();
        this.passwordHash = passwordHash;
        this.rol = Role.USUARIO;
        this.intereses = datos.intereses();
        this.instrumentoDominados = datos.instrumentoDominados();
        this.perfil = new Perfil(this);
        this.activo =true;
    }

    public Set<SalaChatUsuario> getSalasChat() {
        return salasChat;
    }

    public Set<UsuarioMentoria> getMentorias() {
        return mentorias;
    }

    public Perfil getPerfil() {
        return perfil;
    }

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

    public void setPassEncode(String passEncrip) {
        this.passwordHash = passEncrip;
    }

    public void actualizate(DTOActualizacionUsuario datos) {
        if (datos.username() != null && !datos.username().isEmpty()) {
            this.username = datos.username();
        }if (datos.email() != null && !datos.email().isEmpty()){
            this.email = datos.email();
        }if (datos.nombreReal() != null && !datos.nombreReal().isEmpty()){
            this.nombreReal = datos.nombreReal();
        }if (datos.intereses() != null && !datos.intereses().isEmpty()){
            this.intereses = datos.intereses();
        }if (datos.instrumentoDominados() != null && !datos.instrumentoDominados().isEmpty()){
            this.instrumentoDominados = datos.instrumentoDominados();
        }
    }

    public void setActivoFalse() {
        this.activo = false;
    }

    public void setActivoTrue() {
        this.activo = true;
    }

    public boolean isActivo() {
        return activo;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+this.rol.name()));
    }

    public String getPassword() {
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

    public void actualizaRol() {
        this.setRol(com.bach.api.jpa.enums.Role.MENTOR);
    }
//Para que no de error DTORespuestaUsuario, cambiar esto


    public Set<InteresMusical> getIntereses() {
        return intereses;
    }

    public Set<Instrumento> getInstrumentoDominados() {
        return instrumentoDominados;
    }
}