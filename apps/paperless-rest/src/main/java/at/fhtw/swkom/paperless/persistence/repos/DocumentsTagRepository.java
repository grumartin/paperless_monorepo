package at.fhtw.swkom.paperless.persistence.repos;

import at.fhtw.swkom.paperless.persistence.entities.DocumentsTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DocumentsTagRepository extends JpaRepository<DocumentsTag, Integer> {
    Optional<DocumentsTag> findByName(String name);
}
