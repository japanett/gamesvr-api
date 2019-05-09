package gamesvrapi.rest.api.controllers;

import static gamesvrapi.rest.api.security.SecurityConstants.HEADER_STRING;
import static org.springframework.http.HttpStatus.CREATED;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import gamesvrapi.rest.api.dto.PacientPerformanceDTO;
import gamesvrapi.rest.api.entities.PacientEntity;
import gamesvrapi.rest.api.service.PacientService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pacients")
public class PacientController {

  @Autowired
  private final PacientService patientService;

  // Only possible for Therapist
  @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(CREATED)
  public PacientEntity create(@Valid @RequestHeader(HEADER_STRING) final String token,
      @Valid @RequestBody @NonNull final PacientEntity patient) {
    return this.patientService.create(token, patient);
  }

  // Only possible for Therapist
  @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public List<PacientEntity> getPatientsByFilter(
      @Valid @RequestHeader(HEADER_STRING) final String token,
      @RequestParam(required = false) final String id) {
    return this.patientService.getPatientsByFilter(token, id);
  }

  // Only possible for Therapist
  @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public PacientEntity putPatient(@Valid @RequestHeader(HEADER_STRING) final String token,
      @PathVariable final String id, @RequestBody @NonNull final PacientEntity patient) {
    return this.patientService.update(token, id, patient);
  }

  // Only possible for Therapist
  @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public PacientEntity deletePatient(@Valid @RequestHeader(HEADER_STRING) final String token,
      @PathVariable final String id) {
    return this.patientService.delete(token, id);
  }

  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public List<PacientPerformanceDTO> getPacientPerformance(
      @Valid @RequestHeader(HEADER_STRING) final String token,
      @RequestParam(required = false) final String id) {
    log.info("Searching pacient performance history");
    return this.patientService.getPatientPerformance(token, id);
  }
}

