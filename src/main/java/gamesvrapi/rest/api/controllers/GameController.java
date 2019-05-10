package gamesvrapi.rest.api.controllers;

import gamesvrapi.rest.api.entities.GameEntity;
import gamesvrapi.rest.api.service.GameService;
import gamesvrapi.rest.api.web.request.AddGamePlatformRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static gamesvrapi.rest.api.security.SecurityConstants.HEADER_STRING;
import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/games")
public class GameController {

  @Autowired private final GameService gameService;

  // Only possible for Admin
  @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(CREATED)
  public GameEntity create(
      @Valid @RequestHeader(HEADER_STRING) final String token,
      @Valid @RequestBody @NonNull final GameEntity game) {
    return this.gameService.create(token, game);
  }

  // Only possible for Admin
  @PatchMapping(path = "/{id}/update-platforms", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public GameEntity addPlatform(
      @Valid @RequestHeader(HEADER_STRING) final String token,
      @Valid @PathVariable final Long id,
      @Valid @RequestBody @NonNull final AddGamePlatformRequest request) {
    return this.gameService.updateGamePlatforms(token, id, request);
  }

  // Only possible for Admin
  @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public GameEntity delete(
      @Valid @RequestHeader(HEADER_STRING) final String token, @Valid @PathVariable final Long id) {
    return this.gameService.delete(token, id);
  }

  // No auth needed
  @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public List<GameEntity> getGames() {
    return this.gameService.getGames();
  }
}
