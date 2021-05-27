package com.mercadolivre.usuario;

import com.mercadolivre.config.security.Senha;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;
import java.util.Collection;

@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String emailLogin;
    private String senha;
    private Instant dataCadastro;

    @Deprecated
    public Usuario(){}

    public Usuario(String emailLogin, Senha senha){

        this.emailLogin = emailLogin;
        this.senha = senha.hash();
        this.dataCadastro= Instant.now();
    }


    public Long getId() {
        return id;
    }

    public String getEmailLogin() {
        return emailLogin;
    }

    public String getSenha() {
        return senha;
    }

    public Instant getDataCadastro() {
        return dataCadastro;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.emailLogin;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
