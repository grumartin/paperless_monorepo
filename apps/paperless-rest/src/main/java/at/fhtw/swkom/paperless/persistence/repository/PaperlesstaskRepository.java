package at.fhtw.swkom.paperless.persistence.repository;

import at.fhtw.swkom.paperless.persistence.entities.Paperlesstask;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaperlesstaskRepository extends JpaRepository<Paperlesstask, Integer> {
}
