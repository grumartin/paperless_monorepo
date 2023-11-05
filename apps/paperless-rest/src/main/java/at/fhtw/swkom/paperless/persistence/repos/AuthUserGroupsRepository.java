package at.fhtw.swkom.paperless.persistence.repos;

import at.fhtw.swkom.paperless.persistence.entities.AuthUserGroups;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthUserGroupsRepository extends JpaRepository<AuthUserGroups, Integer> {
}
