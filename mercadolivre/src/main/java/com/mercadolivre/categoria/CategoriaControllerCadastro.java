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

        Categoria categoria = categoriaDtoRequest.toModel();

        if(categoriaDtoRequest.getIdCategoriaPrincipal() != null){

            Optional<Categoria> categoriaPrincipal = categoriaRepository.findById(categoriaDtoRequest.getIdCategoriaPrincipal());
            categoria.atrelarCategoriaPrincipal(categoriaPrincipal.get());

        }

        categoriaRepository.save(categoria);

        return  ResponseEntity.ok().build();

    }

}
