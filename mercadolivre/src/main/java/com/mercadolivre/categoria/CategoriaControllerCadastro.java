package com.mercadolivre.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaControllerCadastro {

    @Autowired
    CategoriaRepository categoriaRepository;

    @PostMapping
    public ResponseEntity<?> cadastro(@RequestBody @Valid  CategoriaDtoRequest categoriaDtoRequest){

        Categoria categoria = categoriaDtoRequest.toModel(categoriaDtoRequest);

        if(categoriaDtoRequest.getIdCategoriaPrincipal() != null){

            Categoria categoriaPrincipal = categoriaRepository.getById(categoriaDtoRequest.getIdCategoriaPrincipal());
            categoria.atrelarCategoriaPrincipal(categoriaPrincipal);

        }

        categoriaRepository.save(categoria);

        return  ResponseEntity.ok().build();

    }

}
