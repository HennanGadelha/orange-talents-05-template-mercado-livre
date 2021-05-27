package com.mercadolivre.usuario;

public class TokenUsuarioDto {

	private String token;
	private String tipo;
	
	public TokenUsuarioDto(String token, String tipo) {
		
		this.token = token;
		this.tipo = tipo;
		
	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}
	
	
	
	
}
