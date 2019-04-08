package gamesvrapi.rest.api.repository.Admin;

import gamesvrapi.rest.api.entities.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

    AdminEntity findByUsername (final String username);
}
