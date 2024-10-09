package br.com.grupo5.trabalho_final.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.grupo5.trabalho_final.services.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/teste")
public class TesteController {

	@Autowired
    EmailService emailService;
	
	@GetMapping
	public String olaMundo() {
		emailService.writerTeste();
		return "Email enviado com sucesso!";
	}

}
