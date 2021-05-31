package com.mercadolivre.fluxoCompra;

import com.mercadolivre.produto.Produto;
import com.mercadolivre.usuario.Usuario;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraDtoRequest {

    @Positive
    private int quantidade;
    @NotNull
    private Long idProduto;

    @NotNull
    private GatewayPagamento gatewayPagamento;

    public CompraDtoRequest(int quantidade, Long idProduto, GatewayPagamento gatewayPagamento) {
        this.quantidade = quantidade;
        this.idProduto = idProduto;
        this.gatewayPagamento = gatewayPagamento;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }

    @Override
    public String toString() {
        return "CompraDtoRequest{" +
                "quantidade=" + quantidade +
                ", idProduto=" + idProduto +
                '}';
    }

    public Compra toModel(Produto produto, int quantidade, Usuario comprador, GatewayPagamento gatewayPagamento) {

        return new Compra(produto,quantidade,comprador, gatewayPagamento);
    }
}
