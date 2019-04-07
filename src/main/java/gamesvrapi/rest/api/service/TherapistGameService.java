package gamesvrapi.rest.api.service;

import java.util.List;

import gamesvrapi.rest.api.exceptions.ResourceNotFoundException;
import gamesvrapi.rest.api.model.GameEntity;
import gamesvrapi.rest.api.model.TherapistEntity;
import gamesvrapi.rest.api.repository.Therapist.TherapistGameEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TherapistGameService {

    @Autowired
    private final TokenInterceptorService tokenInterceptorService;

    @Autowired
    private final TherapistGameEntityRepository therapistGameEntityRepository;

    public List<GameEntity> getGames (final String token) {
        TherapistEntity therapist = tokenInterceptorService.translateTherapistToken(token);

        List<GameEntity> games = therapistGameEntityRepository.findAll();

        if (games.isEmpty()) {
            log.warn("TherapistGameService, m=getGames, No Games found!");
            throw new ResourceNotFoundException("TherapistGameService, m=getGames, e: ResourceNotFound!");
        }
        return games;
    }

}
