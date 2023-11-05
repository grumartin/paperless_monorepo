package at.fhtw.swkom.paperless.persistence.repos;

import at.fhtw.swkom.paperless.persistence.entities.DjangoContentType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DjangoContentTypeRepository extends JpaRepository<DjangoContentType, Integer> {
}
