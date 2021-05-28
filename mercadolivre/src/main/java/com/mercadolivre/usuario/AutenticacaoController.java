package com.mercadolivre.usuario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolivre.config.security.TokenUsuario;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {



    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUsuario tokenUsuario;


    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody UsuarioDtoLogin usuarioDtoLogin){

        UsernamePasswordAuthenticationToken dadosLogin = usuarioDtoLogin.converter();

        try {
            Authentication authentication =  authenticationManager.authenticate(dadosLogin);
            String token = tokenUsuario.gerarToken(authentication);
            return ResponseEntity.ok(new TokenUsuarioDto(token, "Bearer"));
        }catch (AuthenticationException ex){

            return ResponseEntity.badRequest().build();
        }

        
    }


}
