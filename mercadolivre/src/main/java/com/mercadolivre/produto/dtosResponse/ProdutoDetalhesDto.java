package com.mercadolivre.produto.dtosResponse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.mercadolivre.caracteristica.CaracteristicaDtoResponse;
import com.mercadolivre.produto.Produto;
import com.mercadolivre.produto.opiniao.OpiniaoDtoResponse;
import com.mercadolivre.produto.perguntas.PerguntaDtoResponse;

public class ProdutoDetalhesDto {

	private String nome;
	private BigDecimal preco;
	private Set<String> imagens = new HashSet<>();
	private Set<CaracteristicaDtoResponse> caracteristicas = new HashSet<>();
	private String descricao;
	private Integer numeroDeAvaliacoes;
	private Double mediaNotas;
	private Set<OpiniaoDtoResponse> opinioes = new HashSet<>();
	private Set<PerguntaDtoResponse> perguntasDoProduto = new HashSet<>();

	public ProdutoDetalhesDto( Produto produto) {
		
		this.nome = produto.getNome();
		this.preco = produto.getValor();
		
		this.imagens = produto.getImagens().stream().map(imagem -> 
		imagem.getLinkImagem()).collect(Collectors.toSet());
		
		this.caracteristicas = produto.getCaracteristicas().stream().map(caracteristica 
				->  new CaracteristicaDtoResponse(caracteristica)).collect(Collectors.toSet());
		
		this.descricao = produto.getDescricao();
		
		this.perguntasDoProduto = produto.getPerguntas().stream().map( pergunta 
				-> new PerguntaDtoResponse(pergunta.getTitulo(),pergunta.getDataPergunta()))
				.collect(Collectors.toSet());
		
		this.opinioes = produto.getOpinioes().stream().map(opiniao 
				-> new OpiniaoDtoResponse(opiniao)).collect(Collectors.toSet());
		
		this.mediaNotas = calculaMedia();
		
		this.numeroDeAvaliacoes = opinioes.size();
		
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public Set<String> getImagens() {
		return imagens;
	}

	public String getDescricao() {
		return descricao;
	}

	public Set<CaracteristicaDtoResponse> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<PerguntaDtoResponse> getPerguntasDoProduto() {
		return perguntasDoProduto;
	}

	public Set<OpiniaoDtoResponse> getOpinioes() {
		return opinioes;
	}
	

	public Integer getNumeroDeAvaliacoes() {
		return numeroDeAvaliacoes;
	}

	public Double getMediaNotas() {
		return mediaNotas;
	}

	public Double calculaMedia() {
		
		List<Integer> notas = new ArrayList<>();
		Integer somaNotas = 0;
		
		for(OpiniaoDtoResponse opiniao : opinioes) {
			notas.add(opiniao.getNota());	
		}
		
		for(int i =0; i<notas.size(); i++) {
			
			somaNotas += notas.get(i);
		}
		
		Double media =  ((double)somaNotas/(double)notas.size());
		
		return media;
	}

}
