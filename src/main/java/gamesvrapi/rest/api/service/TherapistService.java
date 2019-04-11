package gamesvrapi.rest.api.service;

import java.util.List;

import gamesvrapi.rest.api.dto.TokenDTO;
import gamesvrapi.rest.api.entities.AdminEntity;
import gamesvrapi.rest.api.entities.TherapistEntity;
import gamesvrapi.rest.api.exceptions.DuplicateEntryException;
import gamesvrapi.rest.api.exceptions.ExpectationFailedException;
import gamesvrapi.rest.api.exceptions.ResourceNotFoundException;
import gamesvrapi.rest.api.repository.TherapistRepository;
import gamesvrapi.rest.api.security.JWTService;
import gamesvrapi.rest.api.web.request.PatchTherapistRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TherapistService {

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private final TokenInterceptorService tokenInterceptorService;

    @Autowired
    private final TherapistRepository therapistRepository;

    public TokenDTO newSession (final String username, final String password) {
        TherapistEntity therapist = therapistRepository.findByUsername(username);
        try {
            if (!this.bCryptPasswordEncoder.matches(password, therapist.getPassword())) {
                throw new ExpectationFailedException("Incorrect password, username={" + username + "}");
            }
        } catch (java.lang.NullPointerException exception) {
            throw new ResourceNotFoundException("Therapist username={" + username + "} not found!");
        }
        return TokenDTO.builder()
                .japanetToken(JWTService.generateToken(therapist.getId(), "THERAPIST"))
                .build();
    }

    public TherapistEntity create (final TherapistEntity therapist) {
        try {
            log.info("============== THERAPIST CREATED ==============");
            log.info("{}", therapist);
            therapist.setPassword(this.bCryptPasswordEncoder.encode(therapist.getPassword()));
            return therapistRepository.save(therapist);
        } catch (DataIntegrityViolationException exception) {
            throw new DuplicateEntryException("Duplicate username or email");
        }
    }

    public TherapistEntity get (final String token) {
        return tokenInterceptorService.translateTherapistToken(token);
    }

    @Transactional
    public TherapistEntity delete (final String token) {
        TherapistEntity therapist = tokenInterceptorService.translateTherapistToken(token);
        therapistRepository.delete(therapist);
        return therapist;
    }

    @Transactional
    public TherapistEntity patch (final String token,
            final PatchTherapistRequest req) {
        TherapistEntity therapist = tokenInterceptorService.translateTherapistToken(token);
        therapist.setName(req.getName());
        therapist.setEmail(req.getEmail());
        return therapistRepository.save(therapist);
    }

    // Only possible for admin
    public List<TherapistEntity> getAll (final String token) {
        AdminEntity admin = tokenInterceptorService.translateAdminToken(token);
        final var therapists = this.therapistRepository.findAll();
        if (therapists.isEmpty()) {
            log.warn("AdminService, m=getAllTherapist, No Therapists found!");
            throw new ResourceNotFoundException("No Therapists found!");
        }
        return therapists;
    }

}
