package com.example.ml.mail.service;

import org.springframework.mail.SimpleMailMessage;


public interface EmailService {
	void sendEmail(SimpleMailMessage emailMsg);
}
