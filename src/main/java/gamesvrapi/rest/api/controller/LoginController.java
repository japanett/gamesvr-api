package gamesvrapi.rest.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gamesvrapi.rest.api.mapper.AuthenticationMapper;
import gamesvrapi.rest.api.service.TherapistService;
import gamesvrapi.rest.api.web.request.TherapistLoginRequest;
import gamesvrapi.rest.api.web.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginController {

    private final TherapistService therapistService;

    private final AuthenticationMapper mapper;

    @PostMapping("/therapist")
    public AuthenticationResponse login(@RequestBody final TherapistLoginRequest therapistRequest) {
        return this.mapper.toResponse(this.therapistService.login(
                therapistRequest.getUsername(),
                therapistRequest.getPassword()
        ));
    }
}
