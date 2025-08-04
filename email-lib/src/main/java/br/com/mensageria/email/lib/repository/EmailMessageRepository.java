package br.com.mensageria.email.lib.repository;

import br.com.mensageria.email.lib.entity.EmailMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author murilo.felipin
 */
@Repository
public interface EmailMessageRepository extends JpaRepository<EmailMessageEntity, Long> {
    
}
