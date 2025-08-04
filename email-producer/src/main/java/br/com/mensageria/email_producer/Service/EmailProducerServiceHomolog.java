package br.com.mensageria.email_producer.Service;

import br.com.mensageria.email.lib.dto.EmailRequest;
import br.com.mensageria.email.lib.repository.EmailMessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 *
 * @author murilo.felipin
 */
@Profile("test")
@Service
public class EmailProducerServiceHomolog extends EmailProducerServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(EmailProducerServiceHomolog.class);
    
    public EmailProducerServiceHomolog(
            RabbitTemplate rabbitTemplate,
            @Value("${app.rabbitmq.exchange}") String exchange,
            @Value("${app.rabbitmq.routingkey}") String routingKey,
            EmailMessageRepository emailMessageRepository) {
        super(rabbitTemplate, exchange, routingKey, emailMessageRepository);
    }
    
    public void enviarParaFila(EmailRequest emailRequest) throws Exception {
        try {
            EmailRequest modificadoParaTestes = new EmailRequest(
                    emailRequest.id(), 
                    emailRequest.aplicacaoQueEnviou(),
                    emailRequest.operacaoDaAplicacao(),
                    emailRequest.usuarioResponsavel(),
                    emailRequest.remetente(),
                    emailRequest.destinatarios(),
                    "EMAIL DE TESTE - DESCONSIDERAR - " + emailRequest.assunto(),
                    emailRequest.conteudoHtml(),
                    emailRequest.anexos()
            );
            super.enviarParaFila(modificadoParaTestes);
        }catch(Exception ex){
            log.error("Erro ao modificar a mensagem de testes do RabbitMQ..Erro - " + ex.getMessage());
        }
    }
}
