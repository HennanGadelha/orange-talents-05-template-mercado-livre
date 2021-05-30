package com.mercadolivre.produto;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolivre.produto.opiniao.Opiniao;
import com.mercadolivre.produto.opiniao.OpiniaoDtoRequest;
import com.mercadolivre.produto.opiniao.OpiniaoRepository;
import com.mercadolivre.usuario.Usuario;

@RestController
@RequestMapping("/produtos")
public class AdicionaOpniaoController {

	@Autowired
	OpiniaoRepository opniaoRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	
	
	@PostMapping("/{id}/opinioes")
	public ResponseEntity<?> adicionaOpniao(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario, 
			@RequestBody @Valid  OpiniaoDtoRequest opniaoDtoRequest
			){
		
		Optional<Produto> produtoAvaliado =  produtoRepository.findById(id);
		
		if(produtoAvaliado.isEmpty()) return ResponseEntity.notFound().build();
		
		Opiniao opiniao = opniaoDtoRequest.toModel(produtoAvaliado.get(), usuario);
		
		opniaoRepository.save(opiniao);
		
		return ResponseEntity.ok().build();
		
	}
	
}
