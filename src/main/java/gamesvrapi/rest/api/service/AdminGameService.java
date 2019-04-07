package gamesvrapi.rest.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

import gamesvrapi.rest.api.exceptions.DuplicateEntryException;
import gamesvrapi.rest.api.exceptions.ResourceNotFoundException;
import gamesvrapi.rest.api.model.AdminEntity;
import gamesvrapi.rest.api.model.GameEntity;
import gamesvrapi.rest.api.repository.Admin.AdminGameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
            throw new DuplicateEntryException("AdminGameService, m=createGame, e=Duplicate game name!");
        }
    }

    public List<GameEntity> getGames (final String token) {
        AdminEntity admin = tokenInterceptorService.translateAdminToken(token);
        final var games = this.adminGameRepository.findAll();
        if (games.isEmpty()) {
            log.warn("AdminGameService, m=getGames, No Games found!");
            throw new ResourceNotFoundException("AdminGameService, m=getGames, e: ResourceNotFound!");
        }
        return games;

    }

}
