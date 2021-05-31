package com.mercadolivre.fluxoCompra;

import com.mercadolivre.produto.Produto;
import com.mercadolivre.produto.ProdutoRepository;
import com.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import  org.springframework.validation.BindException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/compras")
public class FinalizaCompraController {

    @Autowired
    CompraRepository compraRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @PostMapping
    public String realizaCompra(@RequestBody @Valid  CompraDtoRequest compraDtoRequest,
                                @AuthenticationPrincipal Usuario comprador,
                                UriComponentsBuilder uriComponentsBuilder) throws BindException {

        Optional<Produto> produtoComprado = produtoRepository.findById(compraDtoRequest.getIdProduto());

        boolean produtoAbatidoEmEstoque = produtoComprado.get().retiradaEstoque(compraDtoRequest.getQuantidade());

        if(produtoAbatidoEmEstoque){

            Compra compra = compraDtoRequest.toModel(produtoComprado.get(), compraDtoRequest.getQuantidade(), comprador,
                    compraDtoRequest.getGatewayPagamento());
            compraRepository.save(compra);

            if(compraDtoRequest.getGatewayPagamento().equals(GatewayPagamento.PAGSEGURO)){

                UriComponents urlDeRetornoPagseguro= uriComponentsBuilder.path("/retorno-pagseguro/{id}")
                        .buildAndExpand(compra.getId().toString());

                    return "pagseguro.com/" + compra.getId() + "?redirectUrl=" + urlDeRetornoPagseguro;

            } else {

                UriComponents urlDeRetornoPaypall= uriComponentsBuilder.path("/retorno-pagseguro/{id}")
                        .buildAndExpand(compra.getId().toString());

                return "pagseguro.com/" + compra.getId() + "?redirectUrl=" + urlDeRetornoPaypall;
            }
        }

        BindException quantidadeIndiponivelNoEstoque = new BindException(compraDtoRequest, "compraDtoRequest");
        quantidadeIndiponivelNoEstoque.reject(null, "Quantidade insdiponivel em estoque");

        throw quantidadeIndiponivelNoEstoque;
    }


}
