package gamesvrapi.rest.api.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gamesvrapi.rest.api.entities.TherapyEntity;

@Repository
public interface TherapyRepository extends JpaRepository<TherapyEntity, Long> {

  Optional<List<TherapyEntity>> findByPacientId(String pacientId);

  @Override
  Optional<TherapyEntity> findById(Long therapyId);

  Optional<TherapyEntity> findByPacientIdAndId(String pacientId, Long id);

}
