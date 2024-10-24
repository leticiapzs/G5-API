package br.com.grupo5.trabalho_final.security.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import br.com.grupo5.trabalho_final.security.entities.Cliente;
import br.com.grupo5.trabalho_final.security.entities.Loja;

@Component
public class EmailService {

	@Autowired
	public JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String emailRemetente;

	@Value("${spring.mail.host}")
	private String emailServerHost;

	@Value("${spring.mail.port}")
	private Integer emailServerPort;

	@Value("${spring.mail.password}")
	private String emailPassword;

	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setUsername(emailRemetente);
		mailSender.setHost(emailServerHost);
		mailSender.setPort(emailServerPort);
		mailSender.setPassword(emailPassword);

		Properties props = mailSender.getJavaMailProperties();

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		return mailSender;
	}

	public String mailCadastroLoja(Loja loja) {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");

		SimpleMailMessage mailMessage = new SimpleMailMessage();

		mailMessage.setTo("debsdebbie90@gmail.com");
		mailMessage.setSubject("Parabéns por cadastrar sua loja!");
		mailMessage.setText("Que bom ter você por aqui!\n"
				+ "Seu cadastro foi realizado com sucesso.\n"
				+ "Veja as suas informações para não esquecer de nada:\n\n"
				+ " - Nome de usuário: " + loja.getFkUser().getUsername()
				+ "\n - E-mail: " + loja.getFkUser().getEmail()
				+ "\n - Nome fantasia: " + loja.getNomeFantasia()
				+ "\n - CNPJ: " + loja.getCnpj()
				+ "\n\nAgradecemos o seu cadastro e damos a você as boas vindas!"
				+ "\nUse a conta que acabou de cadastrar para cadastrar os produtos em sua loja, não perca tempo!"
				+ "\n\n\nAtenciosamente,\nEquipe ShopHub.\n"
				+ currentTime.format(formatter));

		try {
			javaMailSender.send(mailMessage);
			return "E-mail enviado com sucesso";
		} catch (Exception e) {
			return "Erro ao enviar o e-mail" + e.getMessage();
		}
	}
	
	public String mailCadastroCliente(Cliente cliente) {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");

		SimpleMailMessage mailMessage = new SimpleMailMessage();

		mailMessage.setTo("debsdebbie90@gmail.com");
		mailMessage.setSubject("Boas vindas ao ShopHub!");
		mailMessage.setText("Que bom ter você por aqui!\n"
				+ "Seu cadastro foi realizado com sucesso.\n"
				+ "Veja as suas informações para não esquecer de nada:\n\n"
				+ " - Nome de usuário: " + cliente.getUser().getUsername()
				+ "\n - E-mail: " + cliente.getUser().getEmail()
				+ "\n - Nome: " + cliente.getNomeCompleto()
				+ "\n - CPF: " + cliente.getCpf()
				+ "\n\nAgradecemos o seu cadastro e damos a você as boas vindas!"
				+ "\nAproveite que está tudo certinho e vá agora mesmo aproveitar as incríveis ofertas em nossa plataforma!"
				+ "\n\n\nAtenciosamente,\nEquipe ShopHub.\n\n\n"
				+ currentTime.format(formatter));

		try {
			javaMailSender.send(mailMessage);
			return "E-mail enviado com sucesso";
		} catch (Exception e) {
			return "Erro ao enviar o e-mail" + e.getMessage();
		}
	}

	
}
