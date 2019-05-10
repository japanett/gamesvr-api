package gamesvrapi.rest.api.controllers;

import gamesvrapi.rest.api.dto.PacientPerformanceDTO;
import gamesvrapi.rest.api.entities.PacientEntity;
import gamesvrapi.rest.api.service.PacientService;
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
@RequestMapping("/api/pacients")
public class PacientController {

  @Autowired private final PacientService pacientService;

  // Only possible for Therapist
  @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(CREATED)
  public PacientEntity create(
      @Valid @RequestHeader(HEADER_STRING) final String token,
      @Valid @RequestBody @NonNull final PacientEntity Pacient) {
    return this.pacientService.create(token, Pacient);
  }

  // Only possible for Therapist
  @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public List<PacientEntity> getPacientsByFilter(
      @Valid @RequestHeader(HEADER_STRING) final String token,
      @RequestParam(required = false) final String id) {
    return this.pacientService.getPacientsByFilter(token, id);
  }

  // Only possible for Therapist
  @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public PacientEntity putPacient(
      @Valid @RequestHeader(HEADER_STRING) final String token,
      @PathVariable final String id,
      @RequestBody @NonNull final PacientEntity Pacient) {
    return this.pacientService.update(token, id, Pacient);
  }

  // Only possible for Therapist
  @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public PacientEntity deletePacient(
      @Valid @RequestHeader(HEADER_STRING) final String token, @PathVariable final String id) {
    return this.pacientService.delete(token, id);
  }

  @GetMapping(path = "/pacient/performance/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public List<PacientPerformanceDTO> getPacientPerformance(
      @Valid @RequestHeader(HEADER_STRING) final String token,
      @RequestParam(required = false) final String id) {
    log.info("Searching pacient performance history");
    return this.pacientService.getPacientPerformance(token, id);
  }
}
