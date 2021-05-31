package com.mercadolivre.produto.dtosResponse;

import java.util.ArrayList;
import java.util.List;

public class ImagensDtoResponse {

	private List<String> imagens = new ArrayList<>();

	public ImagensDtoResponse(List<String> imagens) {
		
		this.imagens = imagens;
	}

	public List<String> getImagens() {
		return imagens;
	}
	
	
}
