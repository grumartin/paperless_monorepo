package at.fhtw.swkom.paperless.persistence.repository;

import at.fhtw.swkom.paperless.persistence.entities.Uisettings;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UisettingsRepository extends JpaRepository<Uisettings, Integer> {
}
