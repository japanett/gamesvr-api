package gamesvrapi.rest.api.controllers;

import static org.springframework.http.HttpStatus.CREATED;

import gamesvrapi.rest.api.mapper.AuthenticationMapper;
import gamesvrapi.rest.api.service.LoginService;
import gamesvrapi.rest.api.web.request.LoginRequest;
import gamesvrapi.rest.api.web.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private final LoginService loginService;

    @Autowired
    private final AuthenticationMapper mapper;

    @PostMapping("/therapist")
    @ResponseStatus(CREATED)
    public AuthenticationResponse loginTherapist (@RequestBody final LoginRequest request) {
        return this.mapper.toResponse(this.loginService.loginTherapist(
                request.getUsername(),
                request.getPassword()
        ));
    }

    @PostMapping("/admin")
    @ResponseStatus(CREATED)
    public AuthenticationResponse loginAdmin (@RequestBody final LoginRequest request) {
        return this.mapper.toResponse(this.loginService.loginAdmin(
                request.getUsername(),
                request.getPassword()
        ));
    }
}
