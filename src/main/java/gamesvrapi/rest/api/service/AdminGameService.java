package gamesvrapi.rest.api.service;

import java.util.List;

import gamesvrapi.rest.api.entities.AdminEntity;
import gamesvrapi.rest.api.entities.GameEntity;
import gamesvrapi.rest.api.exceptions.DuplicateEntryException;
import gamesvrapi.rest.api.exceptions.ResourceNotFoundException;
import gamesvrapi.rest.api.repository.Admin.AdminGameRepository;
import gamesvrapi.rest.api.web.request.AddGamePlatformRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminGameService {

    @Autowired
    private final TokenInterceptorService tokenInterceptorService;

    @Autowired
    private final AdminGameRepository adminGameRepository;

    public GameEntity createGame (final String token, final GameEntity game) {
        try {

            AdminEntity admin = tokenInterceptorService.translateAdminToken(token);
            game.setCreatedBy(admin.getName());

            return adminGameRepository.save(game);
        } catch (DataIntegrityViolationException exception) {
            throw new DuplicateEntryException("Duplicate game name!");
        }
    }

    public List<GameEntity> getGames (final String token) {

        AdminEntity admin = tokenInterceptorService.translateAdminToken(token);

        final var games = this.adminGameRepository.findAll();

        if (games.isEmpty()) {
            log.warn("AdminGameService, m=getGames, No Games found!");
            throw new ResourceNotFoundException("No games found!");
        }

        return games;

    }

    @Transactional
    public GameEntity updateGamePlatforms (final String token, final Long gameId,
            final AddGamePlatformRequest request) {

        AdminEntity admin = tokenInterceptorService.translateAdminToken(token);

        final GameEntity game = this.adminGameRepository.findById(gameId)
                .orElseThrow(() -> new ResourceNotFoundException("Game not found! id: " + gameId.toString()));

        game.setPlatforms(request.getPlatforms());

        return adminGameRepository.save(game);
    }

}
