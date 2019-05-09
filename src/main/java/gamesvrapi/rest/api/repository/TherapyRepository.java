package gamesvrapi.rest.api.repository;

import java.util.List;
import java.util.Optional;
import gamesvrapi.rest.api.entities.TherapyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TherapyRepository extends JpaRepository<TherapyEntity, Long> {

  Optional<List<TherapyEntity>> findByPatientId(String patientId);

  Optional<TherapyEntity> findById(Long therapyId);

  Optional<TherapyEntity> findByPatientIdAndId(String patientId, Long id);

}
