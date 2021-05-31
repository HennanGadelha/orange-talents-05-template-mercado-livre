package com.mercadolivre.produto;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolivre.produto.dtosResponse.ProdutoDetalhesDto;


@RestController
@RequestMapping("/produtos")
public class DetalhesProdutoController {

	@Autowired
	ProdutoRepository produtoRepository;

	@GetMapping("/{id}/detalhes")
	@Transactional
	public ResponseEntity<ProdutoDetalhesDto> detalhes(@PathVariable Long id) {

		Optional<Produto> produto = produtoRepository.findById(id);

		ProdutoDetalhesDto detalhesProduto = new ProdutoDetalhesDto(produto.get());

		return ResponseEntity.ok().body(detalhesProduto);

	}

}
