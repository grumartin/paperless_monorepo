package at.fhtw.swkom.paperless.persistence.repository;

import at.fhtw.swkom.paperless.persistence.entities.Correspondent;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CorrespondentRepository extends JpaRepository<Correspondent, Integer> {
}
