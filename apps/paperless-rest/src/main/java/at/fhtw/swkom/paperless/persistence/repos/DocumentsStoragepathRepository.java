package at.fhtw.swkom.paperless.persistence.repos;

import at.fhtw.swkom.paperless.persistence.entities.DocumentsStoragepath;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentsStoragepathRepository extends JpaRepository<DocumentsStoragepath, Integer> {
}
