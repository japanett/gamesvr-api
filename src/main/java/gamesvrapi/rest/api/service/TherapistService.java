package gamesvrapi.rest.api.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import gamesvrapi.rest.api.dto.TokenDTO;
import gamesvrapi.rest.api.exceptions.ExpectationFailedException;
import gamesvrapi.rest.api.exceptions.ResourceNotFoundException;
import gamesvrapi.rest.api.model.TherapistEntity;
import gamesvrapi.rest.api.repository.Therapist.TherapistRepository;
import gamesvrapi.rest.api.security.JWTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TherapistService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final TherapistRepository therapistRepository;

    public TherapistEntity createTherapist(final TherapistEntity therapist) {
        log.info("============== USER CREATED ==============");
        log.info("{}", therapist);
        therapist.setPassword(this.bCryptPasswordEncoder.encode(therapist.getPassword()));
        return therapistRepository.save(therapist);
    }

    public TherapistEntity getTherapistById(final Long id) {
        return therapistRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("m=getTherapistById, id : {}, e: ResourceNotFound", id);
                    return new ResourceNotFoundException("m=getTherapistById, id:"+ id + ", e: ResourceNotFound");
                });
    }

    public TokenDTO login(final String username, final String password) {
        TherapistEntity therapist = therapistRepository.findByUsername(username);
        try {
            if (!this.bCryptPasswordEncoder.matches(password, therapist.getPassword())) {
                throw new ExpectationFailedException("Incorrect password, username={" + username + "}");
            }
        } catch (java.lang.NullPointerException exception) {
            throw new ResourceNotFoundException("Therapist username={" + username + "} not found!");
        }
        return TokenDTO.builder()
                .japanetToken(JWTService.generateTherapistToken(therapist.getId()))
                .build();
    }

    public List<TherapistEntity> getAllTherapists() {
        final var therapists = this.therapistRepository.findAll(); //.stream().findAny().orElseThrow(() -> new ResourceNotFoundException("teste"));
        log.debug("{}", therapists.stream().findFirst());
        if (therapists.isEmpty()) {
            log.warn("TherapistService, m=getAllTherapist, No Therapists found!");
            throw new ResourceNotFoundException("TherapistService, m=getAllTherapist, e: ResourceNotFound!");
        }
        return therapists;
    }

}
