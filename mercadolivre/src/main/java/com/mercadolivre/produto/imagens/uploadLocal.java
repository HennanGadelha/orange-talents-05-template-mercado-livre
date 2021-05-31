package com.mercadolivre.produto.imagens;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class uploadLocal {

	public static  Set<String> enviaImg(List<MultipartFile> imagens) {
		// TODO Auto-generated method stub
		
		
		return  imagens.stream().map(imagem -> "wwww.mercadolivrezup.com/imagens/" + imagem.getOriginalFilename())
				.collect(Collectors.toSet());
	}

}
