package at.fhtw.swkom.paperless.persistence.repos;

import at.fhtw.swkom.paperless.persistence.entities.AuthGroup;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthGroupRepository extends JpaRepository<AuthGroup, Integer> {
}
