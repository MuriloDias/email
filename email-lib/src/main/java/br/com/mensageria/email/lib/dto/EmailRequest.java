package br.com.mensageria.email.lib.dto;

import java.util.List;

/**
 *
 * @author murilo.felipin
 */
public record EmailRequest(
    Long id,
    String aplicacaoQueEnviou,
    String operacaoDaAplicacao,
    String usuarioResponsavel,
    String remetente,
    List<String> destinatarios,
    String assunto,
    String conteudoHtml,
    List<Anexo> anexos
) {

public EmailRequest(Long id, String aplicacaoQueEnviou, String operacaoDaAplicacao, String usuarioResponsavel, String remetente, List<String> destinatarios, String assunto, String conteudoHtml, List<Anexo> anexos) {
        this.id = id;
        this.aplicacaoQueEnviou = aplicacaoQueEnviou;
        this.operacaoDaAplicacao = operacaoDaAplicacao;
        this.usuarioResponsavel = usuarioResponsavel;
        this.remetente = remetente;
        this.destinatarios = destinatarios;
        this.assunto = assunto;
        this.conteudoHtml = conteudoHtml;
        this.anexos = anexos;
    }
    
    
public void validate() {
        if (isBlank(remetente)) throw new IllegalArgumentException("Remetente é obrigatório.");
        if (destinatarios == null || destinatarios.isEmpty()) throw new IllegalArgumentException("Lista de destinatários não pode ser vazia.");
        if (isBlank(assunto)) throw new IllegalArgumentException("Assunto é obrigatório.");
        if (isBlank(conteudoHtml)) throw new IllegalArgumentException("Conteúdo HTML é obrigatório.");

        if (anexos != null) {
            for (Anexo anexo : anexos) {
                if (isBlank(anexo.nomeArquivo())) {
                    throw new IllegalArgumentException("Nome do arquivo do anexo não pode ser vazio.");
                }
                if (containsInvalidCharacters(anexo.nomeArquivo())) {
                    throw new IllegalArgumentException("Nome do arquivo do anexo contém caracteres inválidos.");
                }
                if (isBlank(anexo.tipoConteudo())) {
                    throw new IllegalArgumentException("Tipo de conteúdo do anexo é obrigatório.");
                }
                if (isBlank(anexo.conteudoBase64())) {
                    throw new IllegalArgumentException("Conteúdo base64 do anexo é obrigatório.");
                }
            }
        }
    }

    private boolean containsInvalidCharacters(String nomeArquivo) {
        return nomeArquivo.contains("\r") ||
               nomeArquivo.contains("\n") ||
               nomeArquivo.contains("..") ||
               nomeArquivo.contains("/") ||
               nomeArquivo.contains("\\");
    }

    private boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EmailRequest{");
        sb.append("id=").append(id);
        sb.append(", aplicacaoQueEnviou=").append(aplicacaoQueEnviou);
        sb.append(", operacaoDaAplicacao=").append(operacaoDaAplicacao);
        sb.append(", usuarioResponsavel=").append(usuarioResponsavel);
        sb.append(", remetente=").append(remetente);
        sb.append(", destinatarios=").append(destinatarios);
        sb.append(", assunto=").append(assunto);
        sb.append(", conteudoHtml=").append(conteudoHtml);
        sb.append(", anexos=").append(anexos);
        sb.append('}');
        return sb.toString();
    }


    
    
}
