package com.mercadolivre.produto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.mercadolivre.caracteristica.CaracteristicaProduto;
import com.mercadolivre.caracteristica.CaracteristicasDtoRequest;
import com.mercadolivre.categoria.Categoria;
import com.mercadolivre.produto.imagens.ImagemProduto;
import com.mercadolivre.produto.opiniao.Opiniao;
import com.mercadolivre.produto.perguntas.Pergunta;
import com.mercadolivre.produto.perguntas.PerguntaDtoResponse;
import com.mercadolivre.usuario.Usuario;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private BigDecimal valor;
	
	private Integer quantidadeDisponivel;
	private String descricao;
	
	private Instant dataCadastro;
	
	@OneToMany(mappedBy = "produto")
	private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();
	
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<Pergunta> perguntas = new HashSet<>();
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<Opiniao> opinioes = new HashSet<>();
	
	@ManyToOne
	private Categoria categoria;
	
	@ManyToOne
	private Usuario usuario;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<ImagemProduto> imagens = new HashSet<>();
	
	@Deprecated
	public Produto() {}
	
	
	public Produto(String nome, BigDecimal valor, Integer quantidadeDisponivel, 
			String descricao, Categoria categoria, Usuario usuario, 
			Collection<CaracteristicasDtoRequest> caracteristicas) {
		
		this.nome = nome;
		this.valor = valor;
		this.quantidadeDisponivel = quantidadeDisponivel;
		this.descricao = descricao;
		this.categoria = categoria;
		this.dataCadastro = Instant.now();
		this.usuario = usuario;
		
		this.caracteristicas.addAll(caracteristicas.stream()
				.map(caracteristica -> caracteristica.toModel(this))
				.collect(Collectors.toSet()));
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}


	public Set<CaracteristicaProduto> getCaracteristicas() {
		return caracteristicas;
	}

	public void addImg(Set<String> linksImagens) {
		// TODO Auto-generated method stub
		Set<ImagemProduto> imagens = linksImagens.stream().map(img -> 
		new ImagemProduto(this, img)).collect(Collectors.toSet());
		
		this.imagens.addAll(imagens);
	}
	
	
	public boolean pertenceAoUsuario(Usuario proprietarioProduto) {
		
		return usuario.equals(proprietarioProduto);
	}


	public Usuario getProprietario() {
		return usuario;
	}


	public String getNome() {
		return nome;
	}


	public BigDecimal getValor() {
		return valor;
	}


	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}


	public Set<ImagemProduto> getImagens() {
		return imagens;
	}


	public String getDescricao() {
		return descricao;
	}


	public Set<Pergunta> getPerguntas() {
		return perguntas;
	}


	public Set<Opiniao> getOpinioes() {
		return opinioes;
	}


    public boolean retiradaEstoque(int quantidade) {

		if(quantidade <= this.quantidadeDisponivel) {
			this.quantidadeDisponivel -= quantidade;
			return  true;
		}
		return false;
    }
}
