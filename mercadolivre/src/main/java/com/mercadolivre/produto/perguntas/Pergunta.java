package com.mercadolivre.produto.perguntas;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.mercadolivre.produto.Produto;
import com.mercadolivre.usuario.Usuario;

@Entity
public class Pergunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Produto produto;
	
	private Instant dataPergunta;
	
	@Deprecated
	public Pergunta() {}

	public Pergunta(String titulo, @NotNull Usuario usuario, @NotNull Produto produto) {
		this.titulo = titulo;
		this.usuario = usuario;
		this.produto = produto;
		this.dataPergunta = Instant.now();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Produto getProduto() {
		return produto;
	}
	
	
	
	
}
