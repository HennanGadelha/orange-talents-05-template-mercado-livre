package com.mercadolivre.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/cadastro")
public class UsuarioControlerCadastro {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> cadastro(@RequestBody @Valid UsuarioDtoRequest usuarioDtoRequest){

        Usuario usuario = usuarioDtoRequest.toModel(usuarioDtoRequest);
        usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();

    }

}
