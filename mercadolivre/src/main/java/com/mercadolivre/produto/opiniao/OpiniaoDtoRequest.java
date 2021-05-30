package com.mercadolivre.produto.opiniao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.mercadolivre.produto.Produto;
import com.mercadolivre.usuario.Usuario;

public class OpiniaoDtoRequest {

	@Range(min = 1, max = 5)
	private Integer nota;
	
	@NotNull
	@NotEmpty
	@NotBlank
	private String titulo;
	@NotNull
	@NotEmpty
	@NotBlank
	private String descricao;
	private Long idProduto;
	
	public OpiniaoDtoRequest(Integer nota, String titulo, String descricao, Long idProduto) {
		
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.idProduto = idProduto;
		
	}

	public Integer getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public Opiniao toModel(Produto produto, Usuario usuario) {
		// TODO Auto-generated method stub
		return new Opiniao(this.nota, this.titulo, this.descricao, produto, usuario);
	}
	

	
}
