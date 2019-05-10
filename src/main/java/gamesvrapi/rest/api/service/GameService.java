package gamesvrapi.rest.api.service;

import gamesvrapi.rest.api.entities.AdminEntity;
import gamesvrapi.rest.api.entities.GameEntity;
import gamesvrapi.rest.api.exceptions.DuplicateEntryException;
import gamesvrapi.rest.api.exceptions.ResourceNotFoundException;
import gamesvrapi.rest.api.repository.GameRepository;
import gamesvrapi.rest.api.web.request.AddGamePlatformRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameService {

  @Autowired private final TokenInterceptorService tokenInterceptorService;

  @Autowired private final GameRepository gameRepository;

  public GameEntity create(final String token, final GameEntity game) {
    try {

      final AdminEntity admin = tokenInterceptorService.translateAdminToken(token);
      game.setCreatedBy(admin.getName());

      return gameRepository.save(game);
    } catch (DataIntegrityViolationException exception) {
      throw new DuplicateEntryException("Duplicate game name!");
    }
  }

  public List<GameEntity> getGames() {
    final var games = this.gameRepository.findAll();

    if (games.isEmpty()) {
      log.warn("GameService, m=getGames, No Games found!");
      throw new ResourceNotFoundException("No games found!");
    }
    return games;
  }

  @Transactional
  public GameEntity updateGamePlatforms(
      final String token, final Long id, final AddGamePlatformRequest request) {

    final AdminEntity admin = tokenInterceptorService.translateAdminToken(token);

    final GameEntity game =
        this.gameRepository
            .findById(id)
            .orElseThrow(
                () -> new ResourceNotFoundException("Game not found! id: " + id.toString()));

    game.setPlatforms(request.getPlatforms());

    return gameRepository.save(game);
  }

  @Transactional
  public GameEntity delete(final String token, final Long id) {

    final AdminEntity admin = tokenInterceptorService.translateAdminToken(token);

    final GameEntity game =
        this.gameRepository
            .findById(id)
            .orElseThrow(
                () -> new ResourceNotFoundException("Game not found! id: " + id.toString()));

    game.setPlatforms(Collections.emptyList());

    gameRepository.delete(game);

    return game;
  }
}
