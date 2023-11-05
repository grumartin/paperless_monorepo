package at.fhtw.swkom.paperless.persistence.repos;

import at.fhtw.swkom.paperless.persistence.entities.DocumentsCorrespondent;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentsCorrespondentRepository extends JpaRepository<DocumentsCorrespondent, Integer> {
}
