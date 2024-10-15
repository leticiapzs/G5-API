package br.com.grupo5.trabalho_final.services;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

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

	public String mailWriter() {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");

		SimpleMailMessage mailMessage = new SimpleMailMessage();

		mailMessage.setTo("diogoportelladantas1234@gmail.com");
		mailMessage.setSubject("Não é spam");
		mailMessage.setText("Era Spam.<br>Boa sorte na próxima." + currentTime.format(formatter));

		try {
			javaMailSender.send(mailMessage);
			return "E-mail enviado com sucesso";
		} catch (Exception e) {
			return "Erro ao enviar o e-mail" + e.getMessage();
		}
	}

	public String mailWriterComplex() {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setSubject("Pode abrir, não tem nada esquisito aqui não.");
			helper.setTo("diogoportelladantas1234@gmail.com");

			String emailBody = "<h1>Ta merecendo todos os emails!</h1>"
					+ "<p>Olá! <br>Você está participando de um teste.<br> Obrigado.<br><br><br></p>"
					+ currentTime.format(formatter) + "</p>";

			helper.setText(emailBody, true);
			javaMailSender.send(mimeMessage);
			return "E-mail enviado com sucesso";
		} catch (MessagingException e) {
			return "Falha ao enviar o e-mail" + e.getMessage();
		}
	}

	public void mailSend() {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
		DecimalFormat df = new DecimalFormat("R$ #,##0.00");
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setSubject("Boa noite");
		helper.setTo("diogoportelladantas1234@gmail.com");
		
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("<html>\r\n");
		sBuilder.append("	<body>\r\n");
		sBuilder.append(currentTime.format(formatter));
		sBuilder.append("		<div align:\" center\">\r\n");
		sBuilder.append("			<p>Aula</p>\r\n");
		sBuilder.append("		</div>\r\n");
		sBuilder.append("		<br>\r\n");
		sBuilder.append("		<table border ='2' cellpadding = '2'>\r\n");
		sBuilder.append("			<tr><th>Nome</th><th>Preço</th></tr>\r\n");
		sBuilder.append("			<tr><td>Chiclete mastigado</td><td>" + df.format(1.99) + "</td></tr>\r\n" );
		sBuilder.append("		</table>\r\n");
		sBuilder.append("	</body>\r\n");
		sBuilder.append("</hmtl>");
		
		helper.setText(sBuilder.toString(), true);
		
		javaMailSender.send(message);
		
		} catch (MessagingException e) {
			System.out.println("Erro ao enviar e-mail" + e.getMessage());
		}
	}
}
