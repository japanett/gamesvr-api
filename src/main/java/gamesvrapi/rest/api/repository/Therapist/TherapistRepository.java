package gamesvrapi.rest.api.repository.Therapist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gamesvrapi.rest.api.model.TherapistEntity;

// JPA Documentarion
// https://docs.spring.io/autorepo/docs/spring-data-jpa/current/api/org/springframework/data/jpa/repository/support/SimpleJpaRepository.html
@Repository
public interface TherapistRepository extends JpaRepository<TherapistEntity, Long> {

    Long countPatientsById(Long id);

}
