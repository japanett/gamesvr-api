package gamesvrapi.rest.api.repository.Therapist;

import gamesvrapi.rest.api.model.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TherapistGameEntityRepository extends JpaRepository<GameEntity, Long> {

}
