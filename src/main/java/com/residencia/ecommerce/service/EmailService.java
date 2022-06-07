package com.residencia.ecommerce.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.residencia.ecommerce.dto.PedidoDTO;
import com.residencia.ecommerce.entity.Pedido;
import com.residencia.ecommerce.exception.EmailException;

@Service
@Validated
public class EmailService {
	@Value("spring.mail.host")
	private String mailHost;
	@Value("spring.mail.port")
	private String mailPort;
	@Value("spring.mail.username")
	private String mailUsername;
	@Value("spring.mail.password")
	private String mailPassword;
	@Autowired
	JavaMailSender emailSender;
	
	public void MailService(JavaMailSender javaMailSender) {
		this.emailSender = javaMailSender;
				
	}
	/*public void enviarEmailTexto(String destinatarioEmail, String assunto, String mensagemEmail) {
		SimpleMailMessage sMailMessage = new SimpleMailMessage();
		sMailMessage.setTo(destinatarioEmail);
		sMailMessage.setSubject(assunto);
		sMailMessage.setText(mensagemEmail);
		sMailMessage.setFrom("teste@teste.com");
		
		emailSender.send(sMailMessage);
	}*/
	
	
    public void enviarEmailHtml(Pedido pedido, PedidoDTO pedidoDTO) throws  MessagingException {
       
            MimeMessage mail = emailSender.createMimeMessage();

            String conteudo = "<h1>Recebemos seu pedido!!</h1>"+
            
            		"<div><h3>Cliente: " + pedido.getCliente().getNomeCliente()+"</h3>"+
            		"<h4>Endereço de entrega: "+ pedido.getCliente().getEndereco().getRua()+"</h4>"+
            		"<h4>"+ pedido.getCliente().getEndereco().getBairro() +" " + pedido.getCliente().getEndereco().getNumero()+"</h4>"+
            		"<h4>"+pedido.getCliente().getEndereco().getCidade()+ " "+ pedido.getCliente().getEndereco().getUf()+"</h4></div>"+
            		"<h4>"+pedido.getCliente().getEndereco().getCep()+"</h4>"+
            		"<div><h2>Pedido Nº " + pedido.getIdPedido() + "</h2>" +
            		"<p> Data do pedido:" + pedido.getDataPedido()+ "</p>"+
            		"<p>Data de envio: " + pedido.getDataEnvio() + "</p>" +
            		"<p>Data de entrega: " + pedido.getDataEntrega() + "</p></div>";
            		//"<div><h4>Produto : "+ pedidoDTO.get + "</h4></div>";
            		
                    
            MimeMessageHelper helper = new MimeMessageHelper(mail);
            helper.setTo(pedido.getCliente().getEmail());
            helper.setText(conteudo,true);
            helper.setSubject("Sua compra foi finalizada, " + pedido.getCliente().getNomeCliente());
            helper.setFrom("grupo01.serratec.turma01@gmail.com");
             emailSender.send(mail);
            
    }
	
	public String getMailHost() {
		return mailHost;
	}
	public void setMailHost(String mailHost) {
		this.mailHost = mailHost;
	}
	public String getMailPort() {
		return mailPort;
	}
	public void setMailPort(String mailPort) {
		this.mailPort = mailPort;
	}
	public String getMailUsername() {
		return mailUsername;
	}
	public void setMailUsername(String mailUsername) {
		this.mailUsername = mailUsername;
	}
	public String getMailPassword() {
		return mailPassword;
	}
	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}
	public JavaMailSender getEmailSender() {
		return emailSender;
	}
	public void setEmailSender(JavaMailSender emailSender) {
		this.emailSender = emailSender;
	}
	@Override
	public String toString() {
		return "EmailService [mailHost=" + mailHost + ", mailPort=" + mailPort + ", mailUsername=" + mailUsername
				+ ", mailPassword=" + mailPassword + ", emailSender=" + emailSender + "]";
	}
	
}
