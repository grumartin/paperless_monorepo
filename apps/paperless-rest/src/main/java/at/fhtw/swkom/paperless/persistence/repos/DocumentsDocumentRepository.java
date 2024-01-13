package at.fhtw.swkom.paperless.persistence.repos;

import at.fhtw.swkom.paperless.persistence.entities.DocumentsDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface DocumentsDocumentRepository extends JpaRepository<DocumentsDocument, Integer> {
    Optional<DocumentsDocument> findByTitle(String name);

    Optional<DocumentsDocument> findById(Integer Id);

    List<DocumentsDocument> findByIdIn(List<Integer> ids);

}
