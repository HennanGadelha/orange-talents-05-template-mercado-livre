package com.mercadolivre.produto.perguntas;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;

public class PerguntaDtoResponse {

	private String titulo;
	private LocalDateTime dataPergunta;
	

	public PerguntaDtoResponse(String titulo, LocalDateTime dataPergunta) {
		
		this.titulo = titulo;
		this.dataPergunta = dataPergunta;
		
	}

	public String getTitulo() {
		return titulo;
	}

	public LocalDateTime getDataPergunta() {
		return dataPergunta;
	} 
	
	
	
	
	
}
