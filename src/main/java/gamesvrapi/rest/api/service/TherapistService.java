package gamesvrapi.rest.api.service;

import org.springframework.stereotype.Service;

import java.util.List;

import gamesvrapi.rest.api.exceptions.ResourceNotFoundException;
import gamesvrapi.rest.api.model.TherapistEntity;
import gamesvrapi.rest.api.repository.Therapist.TherapistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TherapistService {

    private final TherapistRepository therapistRepository;

    public TherapistEntity createTherapist(final TherapistEntity therapist) {
        // Logar objeto
        return therapistRepository.save(therapist);
    }

    public TherapistEntity getTherapistById(final Long id) {
        return therapistRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("m=getTherapistById, id : {}, e: ResourceNotFound", id);
                    return new ResourceNotFoundException("m=getTherapistById, id:"+ id + ", e: ResourceNotFound");
                });
    }

    public List<TherapistEntity> getAllTherapists() {
        final var therapists = this.therapistRepository.findAll(); //.stream().findAny().orElseThrow(() -> new ResourceNotFoundException("teste"));

        if (therapists.isEmpty()) {
            log.warn("TherapistService, m=getAllTherapist, No Therapists found!");
            throw new ResourceNotFoundException("TherapistService, m=getAllTherapist, e: ResourceNotFound!");
        }
        return therapists;
    }

}
