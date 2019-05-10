package gamesvrapi.rest.api.repository;

import gamesvrapi.rest.api.entities.TherapyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TherapyRepository extends JpaRepository<TherapyEntity, Long> {

  Optional<List<TherapyEntity>> findByPacientId(String pacientId);

  @Override
  Optional<TherapyEntity> findById(Long therapyId);

  Optional<TherapyEntity> findByPacientIdAndId(String pacientId, Long id);
}
