package gamesvrapi.rest.api.controllers;

import static gamesvrapi.rest.api.security.SecurityConstants.HEADER_STRING;
import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;

import javax.validation.Valid;

import gamesvrapi.rest.api.entities.GameEntity;
import gamesvrapi.rest.api.service.AdminGameService;
import gamesvrapi.rest.api.web.request.AddGamePlatformRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private final AdminGameService adminGameService;

    // Games - Only possible for admin
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(CREATED)
    public GameEntity createGame (@Valid @RequestHeader(HEADER_STRING) final String token,
            @Valid @RequestBody @NonNull final GameEntity game) {
        return this.adminGameService.createGame(token, game);
    }

    // TODO Delete game - Only possible for admin

    // Games - Only possible for admin
    @PatchMapping(path = "/{gameId}/update-platforms", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GameEntity addPlatform (@Valid @RequestHeader(HEADER_STRING) final String token,
            @Valid @PathVariable final Long gameId,
            @Valid @RequestBody @NonNull final AddGamePlatformRequest request) {
        return this.adminGameService.updateGamePlatforms(token, gameId, request);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<GameEntity> getGames () {
        return this.adminGameService.getGames();
    }

}

