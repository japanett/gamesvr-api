package gamesvrapi.rest.api.controllers;

import gamesvrapi.rest.api.entities.TherapyEntity;
import gamesvrapi.rest.api.service.TherapyService;
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
@RequestMapping("/api/therapy")
public class TherapyController {

  @Autowired private final TherapyService therapyService;

  // TODO: All these endpoints are Only possible for Therapist

  @PostMapping(path = "/pacient/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(CREATED)
  public TherapyEntity create(
      @Valid @RequestHeader(HEADER_STRING) final String token,
      @PathVariable final String id,
      @RequestBody final TherapyEntity request) {
    return this.therapyService.create(token, id, request);
  }

  @GetMapping(path = "/pacient/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public List<TherapyEntity> getpacientTherapies(
      @Valid @RequestHeader(HEADER_STRING) final String token, @PathVariable final String id) {
    return this.therapyService.getPacientTherapies(token, id);
  }

  @PatchMapping(
      path = "/{id}/pacient/{pacientId}/{status}",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public TherapyEntity changeTherapyStatus(
      @Valid @RequestHeader(HEADER_STRING) final String token,
      @PathVariable final Long id,
      @PathVariable final String pacientId,
      @PathVariable final String status) {
    return this.therapyService.changeTherapyStatus(token, pacientId, id, status);
  }
}
