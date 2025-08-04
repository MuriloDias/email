package br.com.mensageria.email_consumer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author murilo.felipin
 */
@Configuration
public class RabbitMQConfig {
    
    public static final String QUEUE_NAME = "email-queue";

    @Bean
    public Queue emailQueue() {
        return new Queue(QUEUE_NAME, true);
    }
    
    @Bean
    public MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter(objectMapper);

        DefaultClassMapper classMapper = new DefaultClassMapper();
        classMapper.setTrustedPackages("br.com.mensageria.email.lib.dto");

        Map<String, Class<?>> typeMappings = new HashMap<>();
        typeMappings.put("br.com.mensageria.email.lib.dto.EmailRequest", br.com.mensageria.email.lib.dto.EmailRequest.class);

        classMapper.setIdClassMapping(typeMappings);

        converter.setClassMapper(classMapper);
        return converter;
    }
    
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory,
            MessageConverter messageConverter) {

        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter);
        return factory;
    }
}
