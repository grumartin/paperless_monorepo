package at.fhtw.swkom.paperless.persistence.repos;

import at.fhtw.swkom.paperless.persistence.entities.DjangoSession;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DjangoSessionRepository extends JpaRepository<DjangoSession, String> {
}
