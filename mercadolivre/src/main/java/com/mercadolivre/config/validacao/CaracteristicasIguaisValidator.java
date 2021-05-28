package com.mercadolivre.config.validacao;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mercadolivre.produto.ProdutoDtoRequest;

public class CaracteristicasIguaisValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return ProdutoDtoRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		if(errors.hasErrors()) return;
		
		ProdutoDtoRequest produto = (ProdutoDtoRequest) target;
		
		if(produto.existeCaracteristicasIguais()) {
			
			errors.rejectValue("caracteristicas", null, "Existe categorias iguais em seu cadastro");
		}
		
	}

}
