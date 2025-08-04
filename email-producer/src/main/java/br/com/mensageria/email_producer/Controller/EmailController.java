package br.com.mensageria.email_producer.Controller;

import br.com.mensageria.email.lib.dto.EmailRequest;
import br.com.mensageria.email_producer.Service.EmailProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private final EmailProducerService emailProducerService;

    public EmailController(EmailProducerService emailProducerService) {
        this.emailProducerService = emailProducerService;
    }

    /**
     * Metodo que vai receber JSON para interpretar o email e mandar para a fila da mensageria
     * 
     * @param emailRequest
     * @return 
     */
    @RequestMapping("/enviar")
    @PostMapping
    public ResponseEntity<String> enviarEmail(@RequestBody EmailRequest emailRequest) throws Exception {
        emailProducerService.enviarParaFila(emailRequest);
        return ResponseEntity.accepted().body("E-mail enviado para processamento");
    }

}
