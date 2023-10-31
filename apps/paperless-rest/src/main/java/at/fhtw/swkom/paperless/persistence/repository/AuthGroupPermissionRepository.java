package at.fhtw.swkom.paperless.persistence.repository;

import at.fhtw.swkom.paperless.persistence.entities.AuthGroupPermission;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthGroupPermissionRepository extends JpaRepository<AuthGroupPermission, Integer> {
}
