package com.mercadolivre.categoria;

import javax.persistence.*;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @ManyToOne
    private Categoria categoriaPrincipal;

    public Categoria(){}

    public Categoria(String nome) {
        this.nome = nome;
    }

    public void atrelarCategoriaPrincipal(Categoria categoriaPrincipal){
        this.categoriaPrincipal = categoriaPrincipal;
    }

}
