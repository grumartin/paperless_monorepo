package at.fhtw.swkom.paperless.persistence.repository;

import at.fhtw.swkom.paperless.persistence.entities.Documenttype;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumenttypeRepository extends JpaRepository<Documenttype, Integer> {
}
