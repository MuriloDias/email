package br.com.mensageria.email.lib.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
/**
 *
 * @author murilo.felipin
 */
@Entity
@Table(name = "email_message")
public class EmailMessageEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "aplicacao_que_enviou", nullable = false)
    private String aplicacaoQueEnviou;

    @Column(name = "operacao_da_aplicacao", nullable = false)
    private String operacaoDaAplicacao;

    @Column(name = "usuario_responsavel", nullable = false)
    private String usuarioResponsavel;

    @Column(nullable = false)
    private String remetente;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String destinatarios; // JSON armazenado como String

    @Column(length = 500, nullable = false)
    private String assunto;

    @Column(name = "conteudo_html", columnDefinition = "TEXT", nullable = false)
    private String conteudoHtml;

    @Column(columnDefinition = "TEXT")
    private String anexos; // JSON armazenado como String (opcional)

    @Column(nullable = false)
    private String status = "RECEBIDO";

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Column(name = "data_envio")
    private LocalDateTime dataEnvio;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAplicacaoQueEnviou() { return aplicacaoQueEnviou; }
    public void setAplicacaoQueEnviou(String aplicacaoQueEnviou) { this.aplicacaoQueEnviou = aplicacaoQueEnviou; }

    public String getOperacaoDaAplicacao() { return operacaoDaAplicacao; }
    public void setOperacaoDaAplicacao(String operacaoDaAplicacao) { this.operacaoDaAplicacao = operacaoDaAplicacao; }

    public String getUsuarioResponsavel() { return usuarioResponsavel; }
    public void setUsuarioResponsavel(String usuarioResponsavel) { this.usuarioResponsavel = usuarioResponsavel; }

    public String getRemetente() { return remetente; }
    public void setRemetente(String remetente) { this.remetente = remetente; }

    public String getDestinatarios() { return destinatarios; }
    public void setDestinatarios(String destinatarios) { this.destinatarios = destinatarios; }

    public String getAssunto() { return assunto; }
    public void setAssunto(String assunto) { this.assunto = assunto; }

    public String getConteudoHtml() { return conteudoHtml; }
    public void setConteudoHtml(String conteudoHtml) { this.conteudoHtml = conteudoHtml; }

    public String getAnexos() { return anexos; }
    public void setAnexos(String anexos) { this.anexos = anexos; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public LocalDateTime getDataEnvio() { return dataEnvio; }
    public void setDataEnvio(LocalDateTime dataEnvio) { this.dataEnvio = dataEnvio; }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.aplicacaoQueEnviou);
        hash = 47 * hash + Objects.hashCode(this.operacaoDaAplicacao);
        hash = 47 * hash + Objects.hashCode(this.usuarioResponsavel);
        hash = 47 * hash + Objects.hashCode(this.remetente);
        hash = 47 * hash + Objects.hashCode(this.destinatarios);
        hash = 47 * hash + Objects.hashCode(this.assunto);
        hash = 47 * hash + Objects.hashCode(this.conteudoHtml);
        hash = 47 * hash + Objects.hashCode(this.anexos);
        hash = 47 * hash + Objects.hashCode(this.status);
        hash = 47 * hash + Objects.hashCode(this.dataCriacao);
        hash = 47 * hash + Objects.hashCode(this.dataEnvio);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EmailMessageEntity other = (EmailMessageEntity) obj;
        if (!Objects.equals(this.aplicacaoQueEnviou, other.aplicacaoQueEnviou)) {
            return false;
        }
        if (!Objects.equals(this.operacaoDaAplicacao, other.operacaoDaAplicacao)) {
            return false;
        }
        if (!Objects.equals(this.usuarioResponsavel, other.usuarioResponsavel)) {
            return false;
        }
        if (!Objects.equals(this.remetente, other.remetente)) {
            return false;
        }
        if (!Objects.equals(this.destinatarios, other.destinatarios)) {
            return false;
        }
        if (!Objects.equals(this.assunto, other.assunto)) {
            return false;
        }
        if (!Objects.equals(this.conteudoHtml, other.conteudoHtml)) {
            return false;
        }
        if (!Objects.equals(this.anexos, other.anexos)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.dataCriacao, other.dataCriacao)) {
            return false;
        }
        return Objects.equals(this.dataEnvio, other.dataEnvio);
    }
    
}
