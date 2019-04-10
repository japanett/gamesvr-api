package gamesvrapi.rest.api.controllers;

import static org.springframework.http.HttpStatus.CREATED;

import javax.validation.Valid;

import gamesvrapi.rest.api.entities.AdminEntity;
import gamesvrapi.rest.api.mapper.AuthenticationMapper;
import gamesvrapi.rest.api.service.AdminService;
import gamesvrapi.rest.api.web.request.LoginRequest;
import gamesvrapi.rest.api.web.response.AuthenticationResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private final AdminService adminService;

    @Autowired
    private final AuthenticationMapper authMapper;

    @PostMapping(path = "/session", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(CREATED)
    public AuthenticationResponse login (@RequestBody final LoginRequest request) {
        return this.authMapper.toResponse(this.adminService.login(
                request.getUsername(),
                request.getPassword()
        ));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(CREATED)
    public AdminEntity create (@Valid @RequestBody @NonNull final AdminEntity admin) {
        return this.adminService.create(admin);
    }

}
