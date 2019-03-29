package gamesvrapi.rest.api.repository.Therapist;

import java.util.Optional;

import gamesvrapi.rest.api.model.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TherapistPatientEntityRepository extends JpaRepository<PatientEntity, Long> {

    Optional<PatientEntity> findByTherapistId (long therapistId);

    Optional<PatientEntity> findById (String id);
}
