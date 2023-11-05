package at.fhtw.swkom.paperless.persistence.repos;

import at.fhtw.swkom.paperless.persistence.entities.DocumentsNote;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentsNoteRepository extends JpaRepository<DocumentsNote, Integer> {
}
