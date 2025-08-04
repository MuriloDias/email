package br.com.mensageria.email_producer.Service;

import br.com.mensageria.email.lib.dto.EmailRequest;

/**
 * 
 * @author murilo.felipin
 */
public interface EmailProducerService {
    
    public void enviarParaFila(EmailRequest emailRequest) throws Exception;
    
}
