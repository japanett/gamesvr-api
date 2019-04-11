package gamesvrapi.rest.api.controllers;

import static gamesvrapi.rest.api.security.SecurityConstants.HEADER_STRING;
import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;

import javax.validation.Valid;

import gamesvrapi.rest.api.entities.PatientEntity;
import gamesvrapi.rest.api.service.PatientService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private final PatientService patientService;

    // Only possible for Therapist
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(CREATED)
    public PatientEntity create (@Valid @RequestHeader(HEADER_STRING) final String token,
            @Valid @RequestBody @NonNull final PatientEntity patient) {
        return this.patientService.create(token, patient);
    }

    // Only possible for Therapist
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<PatientEntity> getPatientsByFilter (@Valid @RequestHeader(HEADER_STRING) final String token,
            @RequestParam(required = false) final String id) {
        return this.patientService.getPatientsByFilter(token, id);
    }

    // Only possible for Therapist
    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PatientEntity putPatient (@Valid @RequestHeader(HEADER_STRING) final String token,
            @PathVariable final String id,
            @RequestBody @NonNull final PatientEntity patient) {
        return this.patientService.update(token, id, patient);
    }

    // Only possible for Therapist
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PatientEntity deletePatient (@Valid @RequestHeader(HEADER_STRING) final String token,
            @PathVariable final String id) {
        return this.patientService.delete(token, id);
    }

}

