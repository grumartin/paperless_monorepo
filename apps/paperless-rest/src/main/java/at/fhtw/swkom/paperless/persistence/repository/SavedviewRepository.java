package at.fhtw.swkom.paperless.persistence.repository;

import at.fhtw.swkom.paperless.persistence.entities.Savedview;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SavedviewRepository extends JpaRepository<Savedview, Integer> {
}
