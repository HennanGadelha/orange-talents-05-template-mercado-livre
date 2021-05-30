package com.mercadolivre.produto.imagens;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.mercadolivre.produto.Produto;

@Entity
public class ImagemProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Produto produto;
	private String linkImagem;
	
	
	@Deprecated
	public ImagemProduto() {}
	
	public ImagemProduto(Produto produto, String linkImagem) {
		
		this.produto = produto;
		this.linkImagem = linkImagem;
		
	}
	
	
}
