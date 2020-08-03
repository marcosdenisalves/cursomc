package com.denisalves.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.denisalves.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
		
	void sendEmail(SimpleMailMessage msg);
}
