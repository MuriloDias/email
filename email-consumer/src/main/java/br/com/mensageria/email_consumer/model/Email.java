package br.com.mensageria.email_consumer.model;

import br.com.mensageria.email.lib.dto.Anexo;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;
import java.io.IOException;

/**
 *
 * @author murilo.felipin
 */
public class Email {
    private String aplicacaoQueEnviou;
    private Session session;
    private MimeMessage mime;
    private String assunto;
    private String conteudo;
    private List<String> destinatarios = new ArrayList<>();
    private List<Anexo> anexos = new ArrayList<>();
    
    public Email(Session session, String assunto, String conteudo, List<String> destinatarios ,List<Anexo> anexos) {
        this.session = session;
        this.assunto = assunto;
        this.conteudo = conteudo;
        this.mime = new MimeMessage(session);
        this.destinatarios = destinatarios;
        this.anexos = anexos;
    }
    
    public void addDestinatarios(List<String> emails) {
        for (String email : emails) {
            this.destinatarios.add(email);
        }
    }

    public void setFrom(String from) throws MessagingException {
        this.mime.setFrom(new InternetAddress(from));
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getAssunto() {
        return assunto;
    }

    public List<String> getDestinatarios() {
        return destinatarios;
    }

    public MimeMessage getMime() {
        return mime;
    }

    public String getAplicacaoQueEnviou() {
        return aplicacaoQueEnviou;
    }

    public void setAplicacaoQueEnviou(String aplicacaoQueEnviou) {
        this.aplicacaoQueEnviou = aplicacaoQueEnviou;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public List<Anexo> getAnexos() {
        return anexos;
    }

    public void setAnexos(List<Anexo> anexos) {
        this.anexos = anexos;
    }

    public void enviar() throws MessagingException, IOException {
        MimeMultipart multipart = new MimeMultipart();

        // Parte do corpo HTML
        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(conteudo, "text/html; charset=UTF-8");
        multipart.addBodyPart(bodyPart);

        // Anexos (se houver)
        for (Anexo anexo : anexos) {
            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource dataSource = new ByteArrayDataSource(anexo.conteudoBase64(), anexo.tipoConteudo());
            attachmentPart.setDataHandler(new DataHandler(dataSource));
            attachmentPart.setFileName(anexo.nomeArquivo());
            multipart.addBodyPart(attachmentPart);
        }

        // Setar multipart no MimeMessage
        this.mime.setContent(multipart);
        this.mime.setSubject(assunto, "UTF-8");

        for (String dest : destinatarios) {
            this.mime.addRecipient(Message.RecipientType.TO, new InternetAddress(dest));
        }

        Transport.send(this.mime);
    }

}