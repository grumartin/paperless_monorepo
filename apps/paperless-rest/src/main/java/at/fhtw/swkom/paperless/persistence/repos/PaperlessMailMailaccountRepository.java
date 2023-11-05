package at.fhtw.swkom.paperless.persistence.repos;

import at.fhtw.swkom.paperless.persistence.entities.PaperlessMailMailaccount;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaperlessMailMailaccountRepository extends JpaRepository<PaperlessMailMailaccount, Integer> {
}
