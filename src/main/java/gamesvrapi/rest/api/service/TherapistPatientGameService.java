package gamesvrapi.rest.api.service;

import java.util.List;

import gamesvrapi.rest.api.entities.PatientEntity;
import gamesvrapi.rest.api.exceptions.ResourceNotFoundException;
import gamesvrapi.rest.api.entities.GameEntity;
import gamesvrapi.rest.api.entities.TherapistEntity;
import gamesvrapi.rest.api.repository.Therapist.TherapistGameEntityRepository;
import gamesvrapi.rest.api.repository.Therapist.TherapistPatientEntityRepository;
import gamesvrapi.rest.api.web.request.AddGameRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TherapistPatientGameService {

    @Autowired
    private final TokenInterceptorService tokenInterceptorService;

    @Autowired
    private final TherapistGameEntityRepository therapistGameEntityRepository;

    @Autowired
    private final TherapistPatientEntityRepository therapistPatientEntityRepository;

    public List<GameEntity> getAvailableGames (final String token) {
        TherapistEntity therapist = tokenInterceptorService.translateTherapistToken(token);

        List<GameEntity> games = therapistGameEntityRepository.findAll();

        if (games.isEmpty()) {
            log.warn("TherapistPatientGameService, m=getGames, No Games found!");
            throw new ResourceNotFoundException("TherapistPatientGameService, m=getGames, e: ResourceNotFound!");
        }
        return games;
    }

    public PatientEntity addGame (final String token,
            final String patientId,
            final AddGameRequest request) {
        TherapistEntity therapist = tokenInterceptorService.translateTherapistToken(token);

        return therapistPatientEntityRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("TherapistPatientGameService, m=addGame, e: Patient not found!"));

    }

}
