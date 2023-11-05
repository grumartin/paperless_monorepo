package at.fhtw.swkom.paperless.persistence.repos;

import at.fhtw.swkom.paperless.persistence.entities.DocumentsUisettings;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentsUisettingsRepository extends JpaRepository<DocumentsUisettings, Integer> {
}
