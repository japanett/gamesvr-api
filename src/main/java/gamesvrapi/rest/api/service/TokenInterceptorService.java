package gamesvrapi.rest.api.service;

import gamesvrapi.rest.api.exceptions.ResourceNotFoundException;
import gamesvrapi.rest.api.model.TherapistEntity;
import gamesvrapi.rest.api.repository.Therapist.TherapistRepository;
import gamesvrapi.rest.api.security.JWTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenInterceptorService {

    @Autowired
    private final TherapistRepository therapistRepository;

    public TherapistEntity translateTherapistToken (final String token) {
        long id = JWTService.getIdFromToken(token);
        return therapistRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("m=tokenInterceptor, id : {}, e: ResourceNotFound", id);
                    return new ResourceNotFoundException("m=tokenInterceptor, id:" + id + ", e: ResourceNotFound");
                });
    }
}
