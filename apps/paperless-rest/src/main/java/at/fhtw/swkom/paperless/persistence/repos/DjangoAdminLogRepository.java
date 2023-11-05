package at.fhtw.swkom.paperless.persistence.repos;

import at.fhtw.swkom.paperless.persistence.entities.DjangoAdminLog;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DjangoAdminLogRepository extends JpaRepository<DjangoAdminLog, Integer> {
}
