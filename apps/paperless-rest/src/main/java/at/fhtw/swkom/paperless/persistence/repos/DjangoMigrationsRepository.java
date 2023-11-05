package at.fhtw.swkom.paperless.persistence.repos;


import at.fhtw.swkom.paperless.persistence.entities.DjangoMigrations;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DjangoMigrationsRepository extends JpaRepository<DjangoMigrations, Integer> {
}
