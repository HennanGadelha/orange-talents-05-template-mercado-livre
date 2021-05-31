package com.mercadolivre.fluxoCompra;

import com.mercadolivre.produto.Produto;
import com.mercadolivre.usuario.Usuario;

import javax.persistence.*;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produto produto;

    private int quantidade;

    @ManyToOne
    private Usuario usuario;

    GatewayPagamento gatewayPagamento;

    @Deprecated
    public Compra(){}

    public Compra(Produto produto, int quantidade, Usuario comprador, GatewayPagamento gatewayPagamento){

        this.produto = produto;
        this.quantidade = quantidade;
        this.usuario = comprador;

    }

    public Long getId() {
        return id;
    }
}
