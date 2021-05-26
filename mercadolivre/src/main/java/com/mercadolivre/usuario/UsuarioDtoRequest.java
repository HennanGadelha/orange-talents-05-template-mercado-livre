package com.mercadolivre.usuario;

import com.mercadolivre.config.security.Senha;
import com.mercadolivre.config.validacao.uniqueValue.UniqueValue;
import com.mercadolivre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.Lob;
import javax.validation.constraints.*;
import java.util.Collection;

public class UsuarioDtoRequest implements UserDetails {

    @Email
    @NotEmpty
    @NotNull
    @NotBlank
    @UniqueValue(domainClass = Usuario.class, fieldName = "emailLogin")
    private String emailLogin;

    @Lob
    @NotEmpty
    @NotNull
    @NotBlank
    @Length(min = 6)
    private String senha;


    public UsuarioDtoRequest(String emailLogin, String senha) {
        this.emailLogin = emailLogin;
        this.senha = senha;
    }

    public String getEmailLogin() {
        return emailLogin;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario toModel(UsuarioDtoRequest usuarioDtoRequest){

        return new Usuario(usuarioDtoRequest.getEmailLogin(), new Senha(usuarioDtoRequest.getSenha()));
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
