package gamesvrapi.rest.api.controllers;

import gamesvrapi.rest.api.entities.TherapistEntity;
import gamesvrapi.rest.api.service.TherapistService;
import gamesvrapi.rest.api.web.request.LoginRequest;
import gamesvrapi.rest.api.web.request.PatchTherapistRequest;
import gamesvrapi.rest.api.web.response.AuthenticationResponse;
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
@RequestMapping("/api/therapists")
public class TherapistController {

  @Autowired private final TherapistService therapistService;

  /*
   * @Autowired private final AuthenticationMapper authMapper;
   */
  @PostMapping(path = "/session", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(CREATED)
  public AuthenticationResponse login(@RequestBody final LoginRequest request) {
    return null; /*
                  * this.authMapper.toResponse(this.therapistService.newSession(
                  * request.getUsername(), request.getPassword() ));
                  */
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(CREATED)
  public TherapistEntity createTherapist(
      @Valid @RequestBody @NonNull final TherapistEntity therapist) {
    return this.therapistService.create(therapist);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public TherapistEntity getTherapist(@Valid @RequestHeader(HEADER_STRING) final String token) {
    return this.therapistService.get(token);
  }

  @PatchMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public TherapistEntity patchTherapist(
      @Valid @RequestHeader(HEADER_STRING) final String token,
      @Valid @RequestBody final PatchTherapistRequest req) {
    return this.therapistService.patch(token, req);
  }

  @DeleteMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public TherapistEntity deleteTherapist(@Valid @RequestHeader(HEADER_STRING) final String token) {
    return this.therapistService.delete(token);
  }

  // TODO :Get All Therapists - Only possible for admin
  @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public List<TherapistEntity> getAll(@Valid @RequestHeader(HEADER_STRING) final String token) {
    return this.therapistService.getAll(token);
  }

  // TODO CHANGE PASSWORD

  // TODO RECOVER PASSWORD
}
