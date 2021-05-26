package com.mercadolivre.config.security;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;

public class Senha {

    @NotBlank @Length(min = 6)
    private String senha;


    public Senha(String senha) {
        this.senha = senha;
    }


    public String hash(){
        return new BCryptPasswordEncoder().encode(this.senha);
    }

}
