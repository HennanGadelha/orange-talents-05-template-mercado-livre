package com.mercadolivre.produto.perguntas;

import javax.validation.constraints.NotNull;

import com.mercadolivre.produto.Produto;
import com.mercadolivre.usuario.Usuario;

public class PerguntaDtoRequest {

	@NotNull
	private String titulo;
	
	
	public PerguntaDtoRequest() {}
	
	public PerguntaDtoRequest(String titulo) {
		
		this.titulo = titulo;
	}


	public String getTitulo() {
		return titulo;
	}


	public Pergunta toModel(Usuario usuario, Produto produto) {
		// TODO Auto-generated method stub
		return new Pergunta(this.titulo, usuario, produto);
	}
	
}
