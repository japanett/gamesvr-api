package gamesvrapi.rest.api.repository.Admin;

import gamesvrapi.rest.api.entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminGameRepository extends JpaRepository<GameEntity, Long> {

}
