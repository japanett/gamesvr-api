package gamesvrapi.rest.api.repository;

import java.util.List;
import java.util.Optional;

import gamesvrapi.rest.api.entities.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    Optional<List<PatientEntity>> findByTherapistId (long therapistId);

    Optional<PatientEntity> findById (String id);

}
