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

import com.mercadolivre.emails.Emails;
import com.mercadolivre.produto.perguntas.Pergunta;
import com.mercadolivre.produto.perguntas.PerguntaDtoRequest;
import com.mercadolivre.produto.perguntas.PerguntaRepository;
import com.mercadolivre.usuario.Usuario;

@RestController
@RequestMapping("/produtos")
public class AdicionaPerguntaController {

	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	PerguntaRepository perguntaRepository;
	
	@Autowired
	Emails email;
	
	@PostMapping("/{id}/perguntas")
	public ResponseEntity<?> adicionaPergunta(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario,
			@RequestBody @Valid PerguntaDtoRequest perguntaDto){
		
		Optional<Produto> produto = produtoRepository.findById(id);
		
		if(produto.isEmpty()) return ResponseEntity.notFound().build();
		
		Pergunta pergunta = perguntaDto.toModel(usuario, produto.get());
		
		perguntaRepository.save(pergunta);
		
		email.novaPergunta(pergunta);
		
		return ResponseEntity.ok().build();
	}
	
	
}
