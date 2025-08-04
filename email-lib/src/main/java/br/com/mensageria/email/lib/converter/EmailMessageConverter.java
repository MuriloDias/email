package br.com.mensageria.email.lib.converter;

import br.com.mensageria.email.lib.dto.EmailRequest;
import br.com.mensageria.email.lib.dto.Anexo;
import br.com.mensageria.email.lib.entity.EmailMessageEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author murilo.felipin
 */
public class EmailMessageConverter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static EmailMessageEntity toEntity(EmailRequest request) {
        try {
            EmailMessageEntity entity = new EmailMessageEntity();
            entity.setId(request.id());
            entity.setAplicacaoQueEnviou(request.aplicacaoQueEnviou());
            entity.setOperacaoDaAplicacao(request.operacaoDaAplicacao());
            entity.setUsuarioResponsavel(request.usuarioResponsavel());
            entity.setRemetente(request.remetente());
            entity.setDestinatarios(objectMapper.writeValueAsString(request.destinatarios()));
            entity.setAssunto(request.assunto());
            entity.setConteudoHtml(request.conteudoHtml());
            if (request.anexos() != null) {
                entity.setAnexos(objectMapper.writeValueAsString(request.anexos()));
            }
            return entity;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter EmailRequest para Entity", e);
        }
    }

    public static EmailRequest toRequest(EmailMessageEntity entity) {
        try {
            List<String> destinatarios = Arrays.asList(objectMapper.readValue(entity.getDestinatarios(), String[].class));
            List<Anexo> anexos = null;
            if (entity.getAnexos() != null) {
                anexos = Arrays.asList(objectMapper.readValue(entity.getAnexos(), Anexo[].class));
            }
            return new EmailRequest(
                    entity.getId(),
                    entity.getAplicacaoQueEnviou(),
                    entity.getOperacaoDaAplicacao(),
                    entity.getUsuarioResponsavel(),
                    entity.getRemetente(),
                    destinatarios,
                    entity.getAssunto(),
                    entity.getConteudoHtml(),
                    anexos
            );
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter Entity para EmailRequest", e);
        }
    }
}
