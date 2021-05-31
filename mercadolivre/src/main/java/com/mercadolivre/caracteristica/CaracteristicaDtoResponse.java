package com.mercadolivre.caracteristica;

public class CaracteristicaDtoResponse {

	
	private String nome;
	
	private String descricao;
	
	public CaracteristicaDtoResponse(CaracteristicaProduto carateristica) {
		
		this.nome = carateristica.getNome();
		this.descricao = carateristica.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	
	
}
