package br.com.mensageria.email_consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "br.com.mensageria.email_consumer",
        "br.com.mensageria.email.lib"})
@EnableJpaRepositories(basePackages = "br.com.mensageria.email.lib.repository")
@EntityScan(basePackages = "br.com.mensageria.email.lib.entity")
public class EmailConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailConsumerApplication.class, args);
	}

}
