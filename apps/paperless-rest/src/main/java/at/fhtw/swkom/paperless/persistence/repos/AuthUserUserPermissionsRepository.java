package at.fhtw.swkom.paperless.persistence.repos;

import at.fhtw.swkom.paperless.persistence.entities.AuthUserUserPermissions;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthUserUserPermissionsRepository extends JpaRepository<AuthUserUserPermissions, Integer> {
}
