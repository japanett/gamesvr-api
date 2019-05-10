package gamesvrapi.rest.api.repository;

import gamesvrapi.rest.api.entities.PacientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacientRepository extends JpaRepository<PacientEntity, Long> {

  Optional<List<PacientEntity>> findByTherapistId(long therapistId);

  Optional<PacientEntity> findById(String id);
}
