package com.mercadolivre.emails;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mercadolivre.produto.perguntas.Pergunta;

@Component
public class Emails {

	@Autowired
	private Mailer mailer;

	public void novaPergunta(@NotNull @Valid Pergunta pergunta) {
		// TODO Auto-generated method stub

		mailer.send("<html><body> |Envio de email| </body></html ", " Nova pergunta ",
				pergunta.getUsuario().getEmailLogin(), " novapergunta@mercadolivre.com",
				pergunta.getProduto().getProprietario().getEmailLogin());

	}

}
