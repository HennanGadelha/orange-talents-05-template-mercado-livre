package com.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.mercadolivre.caracteristica.CaracteristicasDtoRequest;
import com.mercadolivre.categoria.Categoria;
import com.mercadolivre.config.validacao.existingId.ExistingID;
import com.mercadolivre.usuario.Usuario;

public class ProdutoDtoRequest {

	@NotNull
	private String nome;
	
	@Positive
	@NotNull
	private BigDecimal valor;
	@Positive
	@NotNull
	private Integer quantidadeDisponivel;
	
	@Size(max = 1000)
	@NotNull
	private String descricao;
	
	@NotNull
	@ExistingID(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoria;
	// private Long idUsuario;

	@Size(min = 3)
	private List<CaracteristicasDtoRequest> caracteristicas = new ArrayList<>();

	public ProdutoDtoRequest(String nome, BigDecimal valor, Integer quantidadeDisponivel, String descricao,
			Long idCategoria) {
		this.nome = nome;
		this.valor = valor;
		this.quantidadeDisponivel = quantidadeDisponivel;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
		this.caracteristicas.addAll(caracteristicas);
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}

	public String getDescricao() {
		return descricao;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public List<CaracteristicasDtoRequest> getCaracteristicas() {
		return caracteristicas;
	}

	public Produto toModel(Usuario usuario, Categoria categoria) {
		// TODO Auto-generated method stub

		return new Produto(this.nome, this.valor, this.quantidadeDisponivel, this.descricao, categoria, usuario,
				caracteristicas);
	}


	public boolean existeCaracteristicasIguais() {

		HashSet<String> caracteristicasIguais = new HashSet<>();

		for (CaracteristicasDtoRequest caracteristica : caracteristicas) {
			if (!caracteristicasIguais.add(caracteristica.getNome()))
				return true;

		}
		return false;
	}

}
