package com.mercadolivre.emails;

public interface Mailer {

	void send(String body, String subject, String nameFrom, String from,String to);

}
