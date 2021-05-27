package com.mercadolivre.usuario;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UsuarioDtoLogin {

	@Email
    private String email;
    
    @Length(min = 6)
    private String senha;

    public UsuarioDtoLogin(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public UsernamePasswordAuthenticationToken converter() {

        return new UsernamePasswordAuthenticationToken(this.email, this.senha);
    }
}
