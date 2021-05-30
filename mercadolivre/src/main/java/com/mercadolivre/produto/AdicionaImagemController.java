package com.mercadolivre.produto;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolivre.config.validacao.CaracteristicasIguaisValidator;
import com.mercadolivre.produto.imagens.ImagemProdutoDto;
import com.mercadolivre.produto.imagens.uploadLocal;
import com.mercadolivre.usuario.Usuario;

@RestController
@RequestMapping("/produtos")
public class AdicionaImagemController {

	@Autowired
	uploadLocal uploadImg;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
		
	@PostMapping("/{id}/imagens")
	public ResponseEntity<?> adicionaImagem(@PathVariable Long id, @Valid ImagemProdutoDto img, @AuthenticationPrincipal Usuario usuario) {
		
		Set<String> linksImagens = uploadLocal.enviaImg(img.getImagens());
		
	    Produto produto = produtoRepository.findById(id).get();
		produto.addImg(linksImagens);
		
		if(!produto.pertenceAoUsuario(usuario)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		
		produtoRepository.save(produto);
		return ResponseEntity.ok().build();
	}
	
	
}
