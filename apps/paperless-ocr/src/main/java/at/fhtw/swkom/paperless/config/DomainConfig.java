package at.fhtw.swkom.paperless.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan("at.fhtw.swkom.paperless.persistence.entities")
@EnableJpaRepositories("at.fhtw.swkom.paperless.persistence.repos")
@EnableTransactionManagement
public class DomainConfig {
}
