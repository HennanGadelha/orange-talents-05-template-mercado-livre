package com.mercadolivre.emails;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component

public class MailerImpl implements Mailer {

	@Override
	public void send(String body, String subject, String nameFrom, String from, String to) {
		// TODO Auto-generated method stub
		
		System.out.println(body);
		System.out.println(subject);
		System.out.println(nameFrom);
		System.out.println(from);
		System.out.println(to);
		
	}

}
