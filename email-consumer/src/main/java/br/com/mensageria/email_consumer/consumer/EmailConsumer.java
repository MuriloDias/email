package br.com.mensageria.email_consumer.consumer;

import br.com.mensageria.email.lib.dto.EmailRequest;
import br.com.mensageria.email.lib.entity.EmailMessageEntity;
import br.com.mensageria.email.lib.repository.EmailMessageRepository;
import br.com.mensageria.email_consumer.config.RabbitMQConfig;
import br.com.mensageria.email_consumer.model.Email;
import br.com.mensageria.email_consumer.service.GerenciarEmail;
import jakarta.persistence.NoResultException;
import java.time.LocalDateTime;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author murilo.felipin
 */
@Component
public class EmailConsumer {
    
    private static final Logger log = LoggerFactory.getLogger(EmailConsumer.class);
    private final GerenciarEmail gerenciarEmail;
    private final EmailMessageRepository emailMessageRepository;

    public EmailConsumer(GerenciarEmail enviarEmail, EmailMessageRepository emailMessageRepository) {
        this.gerenciarEmail = enviarEmail;
        this.emailMessageRepository = emailMessageRepository;
    }

    @Transactional
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(EmailRequest request) throws Exception {
        EmailMessageEntity emailConsultado = null;
        try{
            log.info("Iniciado envio de email - " + request);
            
            Email email = gerenciarEmail.criarMensagem(request);
            gerenciarEmail.enviar(email, request.remetente()); 
            
            emailConsultado = emailMessageRepository.findById(request.id()).orElse(null);
            
            if (emailConsultado != null) {
                emailConsultado.setStatus("ENVIADO");
                emailConsultado.setDataEnvio(LocalDateTime.now());
            }else{
                throw new NoResultException("Objeto de email não encontrado no DB.");
            }
            
            log.info(request.destinatarios().size() + "emails enviados com sucesso..");
            
        }catch(Exception ex){            
            log.error(ex.getMessage());
            throw ex;
        } finally {
            if (emailConsultado != null) {
                emailConsultado.setStatus("ERRO");
                emailMessageRepository.save(emailConsultado);
            }
        }
    }
}
