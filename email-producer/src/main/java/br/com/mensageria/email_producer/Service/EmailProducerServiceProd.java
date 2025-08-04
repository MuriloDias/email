package br.com.mensageria.email_producer.Service;

import br.com.mensageria.email.lib.dto.EmailRequest;
import br.com.mensageria.email.lib.repository.EmailMessageRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 *
 * @author murilo.felipin
 */
@Profile("prod")
@Service
public class EmailProducerServiceProd extends EmailProducerServiceImpl {

    public EmailProducerServiceProd(
            RabbitTemplate rabbitTemplate,
            @Value("${app.rabbitmq.exchange}") String exchange,
            @Value("${app.rabbitmq.routingkey}") String routingKey,
            EmailMessageRepository emailMessageRepository) {
        super(rabbitTemplate, exchange, routingKey, emailMessageRepository);
    }

    
    public void enviarParaFila(EmailRequest emailRequest) throws Exception {
        super.enviarParaFila(emailRequest);
    }
}
