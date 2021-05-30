package com.mercadolivre.produto.opiniao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.mercadolivre.produto.Produto;
import com.mercadolivre.usuario.Usuario;

@Entity	
public class Opiniao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer nota;
	private String titulo;
	private String descricao;
	
	@ManyToOne
	private Produto produto;
	
	@ManyToOne
	private Usuario usuario;
	
	@Deprecated
	public Opiniao() {}
	

	public Opiniao(Integer nota, String titulo, String descricao, Produto produto, Usuario usuario) {
		
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.produto = produto;
		this.usuario = usuario;
	}
	
	
	
	
}
