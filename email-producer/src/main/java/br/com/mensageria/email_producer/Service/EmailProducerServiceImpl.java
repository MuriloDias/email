package br.com.mensageria.email_producer.Service;

import br.com.mensageria.email.lib.converter.EmailMessageConverter;
import br.com.mensageria.email.lib.dto.EmailRequest;
import br.com.mensageria.email.lib.entity.EmailMessageEntity;
import br.com.mensageria.email.lib.repository.EmailMessageRepository;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author murilo.felipin
 */
public class EmailProducerServiceImpl implements EmailProducerService {
    private static final Logger log = LoggerFactory.getLogger(EmailProducerService.class);
    private final EmailMessageRepository emailMessageRepository;
    private final RabbitTemplate rabbitTemplate;
    private String exchange;
    private String routingKey;

    public EmailProducerServiceImpl(
            RabbitTemplate rabbitTemplate,
            @Value("${app.rabbitmq.exchange}") String exchange,
            @Value("${app.rabbitmq.routingkey}") String routingKey,
            EmailMessageRepository emailMessageRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
        this.routingKey = routingKey;
        this.emailMessageRepository = emailMessageRepository;
    }
    
        public void enviarParaFila(EmailRequest emailRequest) throws Exception {
        try {
            emailRequest.validate();
            
            EmailRequest emailRequestSalvo = this.salvarEmail(emailRequest);
            
            rabbitTemplate.convertAndSend(exchange, routingKey, emailRequestSalvo, (MessagePostProcessor) message -> {
                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                return message;
            });
            
            log.info("Email Encaminhado para fila - " + emailRequestSalvo);
        } catch (Exception ex){
            log.error("Erro ao validar ou enviar a mensagem ao RabbitMQ..Erro - " + ex.getMessage());
            throw ex;
        }
    }
    
    public EmailRequest salvarEmail(EmailRequest emailRequest) throws Exception{
        try{
            EmailMessageEntity entity = EmailMessageConverter.toEntity(emailRequest);
            entity.setStatus("RECEBIDO");
            entity.setDataCriacao(LocalDateTime.now());
            emailMessageRepository.save(entity);
            return EmailMessageConverter.toRequest(entity);
        }catch(Exception ex){
            throw new Exception("Erro ao salvar email..Erro - " +ex);
        }
    }
}
