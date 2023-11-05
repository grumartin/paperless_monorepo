package at.fhtw.swkom.paperless.persistence.repos;

import at.fhtw.swkom.paperless.persistence.entities.DocumentsDocumentTags;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentsDocumentTagsRepository extends JpaRepository<DocumentsDocumentTags, Integer> {
}
