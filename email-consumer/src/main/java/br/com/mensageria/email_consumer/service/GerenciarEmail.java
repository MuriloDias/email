package br.com.mensageria.email_consumer.service;

import br.com.mensageria.email.lib.dto.EmailRequest;
import br.com.mensageria.email_consumer.config.EmailProperties;
import br.com.mensageria.email_consumer.model.Email;
import jakarta.mail.*;
import java.io.IOException;
import org.springframework.stereotype.Component;
import java.util.Properties;

/**
 *
 * @author murilo.felipin
 */
@Component
public class GerenciarEmail {
    
    private final EmailProperties properties;

    public GerenciarEmail(EmailProperties properties) {
        this.properties = properties;
    }

    public Session criarSessao() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", String.valueOf(properties.isAuth()));
        props.put("mail.smtp.starttls.enable", String.valueOf(properties.isStarttlsEnable()));
        props.put("mail.smtp.starttls.required", String.valueOf(properties.isStarttlsRequired()));
        props.put("mail.smtp.ssl.trust", properties.getSslTrust());
        props.put("mail.smtp.ssl.protocols", properties.getSslProtocols());
        props.put("mail.smtp.connectiontimeout", properties.getConnectiontimeout());
        props.put("mail.smtp.timeout", properties.getTimeout());
        props.put("mail.smtp.host", properties.getHost());
        props.put("mail.smtp.port", properties.getPort());

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getUsername(), properties.getPassword());
            }
        };

        Session session = Session.getInstance(props, auth);
        //session.setDebug(true);
        return session;
    }

    public Email criarMensagem(EmailRequest emailRequest) {
        Email email = new Email(
                criarSessao(), 
                emailRequest.assunto(), 
                emailRequest.conteudoHtml(), 
                emailRequest.destinatarios(), 
                emailRequest.anexos());
        return email;
    }

    public void enviar(Email email, String remetente) throws IOException {
        try {
            if(remetente.isBlank())
                email.setFrom(System.getProperty("mail.smtp.from"));
            else
                email.setFrom(remetente);
            email.enviar();
        } catch (MessagingException e) {
            throw new RuntimeException("Erro ao enviar e-mail", e);
        }
    }
}
