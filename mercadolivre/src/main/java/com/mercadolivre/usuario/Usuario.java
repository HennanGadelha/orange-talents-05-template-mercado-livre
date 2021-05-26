package com.mercadolivre.usuario;

import com.mercadolivre.config.security.Senha;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class Usuario {

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


}
