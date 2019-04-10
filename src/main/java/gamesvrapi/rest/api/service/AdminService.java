package gamesvrapi.rest.api.service;

import java.util.List;

import gamesvrapi.rest.api.exceptions.DuplicateEntryException;
import gamesvrapi.rest.api.exceptions.ResourceNotFoundException;
import gamesvrapi.rest.api.entities.AdminEntity;
import gamesvrapi.rest.api.entities.TherapistEntity;
import gamesvrapi.rest.api.repository.Admin.AdminRepository;
import gamesvrapi.rest.api.repository.Therapist.TherapistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private final TokenInterceptorService tokenInterceptorService;

    @Autowired
    private final AdminRepository adminRepository;

    @Autowired
    private final TherapistRepository therapistRepository;

    public AdminEntity create (final AdminEntity admin) {
        try {
            admin.setPassword(this.bCryptPasswordEncoder.encode(admin.getPassword()));
            log.info("============== ADMIN CREATED ==============");
            log.info("{}", admin);
            return adminRepository.save(admin);
        } catch (DataIntegrityViolationException exception) {
            throw new DuplicateEntryException("Duplicate username or email");
        }
    }

    public List<TherapistEntity> getTherapists (final String token) {
        AdminEntity admin = tokenInterceptorService.translateAdminToken(token);
        final var therapists = this.therapistRepository.findAll();
        if (therapists.isEmpty()) {
            log.warn("AdminService, m=getAllTherapist, No Therapists found!");
            throw new ResourceNotFoundException("No Therapists found!");
        }
        return therapists;
    }
}
