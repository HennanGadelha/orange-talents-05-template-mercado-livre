package com.mercadolivre.produto;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolivre.caracteristica.CaracteristicaRepository;
import com.mercadolivre.categoria.Categoria;
import com.mercadolivre.categoria.CategoriaRepository;
import com.mercadolivre.config.validacao.CaracteristicasIguaisValidator;
import com.mercadolivre.usuario.Usuario;
import com.mercadolivre.usuario.UsuarioRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoControllerCadastro {

	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	CaracteristicaRepository caracteristicaRepository;
	
	@InitBinder
	public void init(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new CaracteristicasIguaisValidator());
	}
	
	@PostMapping
	public String cadastro(@RequestBody @Valid ProdutoDtoRequest produtoDtoRequest, @AuthenticationPrincipal Usuario usuario) {
		
		Categoria categoria = categoriaRepository.findById(produtoDtoRequest.getIdCategoria()).get();
		
		Produto produto = produtoDtoRequest.toModel(usuario, categoria);
		
		produtoRepository.save(produto);
		caracteristicaRepository.saveAll(produto.getCaracteristicas());
		
		return "cadastrou: " + produtoDtoRequest.toString();
	}
	
}
