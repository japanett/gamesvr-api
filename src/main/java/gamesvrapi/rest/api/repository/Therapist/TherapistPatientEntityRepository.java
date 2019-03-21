package gamesvrapi.rest.api.repository.Therapist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import gamesvrapi.rest.api.model.PatientEntity;

@Repository
public interface TherapistPatientEntityRepository extends JpaRepository<PatientEntity, Long> {

    Optional<PatientEntity> findByTherapistId(long therapistId);
}
