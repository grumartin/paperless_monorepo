package at.fhtw.swkom.paperless.persistence.repository;

import at.fhtw.swkom.paperless.persistence.entities.Storagepath;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StoragepathRepository extends JpaRepository<Storagepath, Integer> {
}
