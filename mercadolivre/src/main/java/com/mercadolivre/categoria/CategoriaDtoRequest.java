package com.mercadolivre.categoria;

import com.mercadolivre.config.validacao.existingId.ExistingID;
import com.mercadolivre.config.validacao.uniqueValue.UniqueValue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CategoriaDtoRequest {

    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    @NotNull
    @NotBlank
    @NotEmpty
    private String nome;

    @Positive
    @ExistingID(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoriaPrincipal;

    public CategoriaDtoRequest(String nome, Long idCategoriaPrincipal) {
        this.nome = nome;
        this.idCategoriaPrincipal = idCategoriaPrincipal;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdCategoriaPrincipal() {
        return idCategoriaPrincipal;
    }


    public Categoria toModel(CategoriaDtoRequest categoriaDtoRequest){
        return new Categoria(categoriaDtoRequest.getNome());
    }


}
