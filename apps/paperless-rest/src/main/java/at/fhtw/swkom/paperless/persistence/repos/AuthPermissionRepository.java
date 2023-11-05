package at.fhtw.swkom.paperless.persistence.repos;

import at.fhtw.swkom.paperless.persistence.entities.AuthPermission;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthPermissionRepository extends JpaRepository<AuthPermission, Integer> {
}
