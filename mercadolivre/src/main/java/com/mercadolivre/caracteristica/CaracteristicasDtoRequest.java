package com.mercadolivre.caracteristica;

import javax.validation.constraints.NotNull;

import com.mercadolivre.produto.Produto;

public class CaracteristicasDtoRequest {

	@NotNull
	private String nome;
	@NotNull
	private String descricao;
	
	public CaracteristicasDtoRequest(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return "Caracteristicas [nome=" + nome + ", descricao=" + descricao + "]";
	}

	public CaracteristicaProduto toModel(Produto produto) {
		// TODO Auto-generated method stub
		return new CaracteristicaProduto(nome, descricao, produto);
	}
	
	
	
	
	
}
