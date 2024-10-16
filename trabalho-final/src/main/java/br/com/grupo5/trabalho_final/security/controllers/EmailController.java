package br.com.grupo5.trabalho_final.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.grupo5.trabalho_final.security.services.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {
	
	@Autowired
	EmailService emailService;
	
	@GetMapping
	public String email() {
		emailService.mailWriter();
		return "O primeiro foi!";
	}
	
	@GetMapping("/outroemail")
	public String outroEmail() {
		emailService.mailWriterComplex();
		return "O segundo foi!";
	}
	
	@GetMapping("/maisumemail")
	public String maisUmEmail() {
		emailService.mailSend();
		return "O terceiro tá funcionando!";
	}

}
