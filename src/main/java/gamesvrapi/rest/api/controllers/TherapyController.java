package gamesvrapi.rest.api.controllers;

import static gamesvrapi.rest.api.security.SecurityConstants.HEADER_STRING;
import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;

import javax.validation.Valid;

import gamesvrapi.rest.api.entities.TherapyEntity;
import gamesvrapi.rest.api.service.TherapistPatientGameService;
import gamesvrapi.rest.api.service.TherapistPatientService;
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
@RequestMapping("/api/therapies")
public class TherapyController {

    @Autowired
    private final TherapistPatientService therapistPatientService;

    @Autowired
    private final TherapistPatientGameService therapistPatientGameService;

    // TODO: All these endpoints are Only possible for Therapist

    @PostMapping(path = "/patients/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(CREATED)
    public TherapyEntity createTherapy (@Valid @RequestHeader(HEADER_STRING) final String token,
            @PathVariable final String id,
            @RequestBody final TherapyEntity request) {
        return this.therapistPatientGameService.addTherapy(token, id, request);
    }

    @GetMapping(path = "/{id}/therapies", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<TherapyEntity> getTherapies (@Valid @RequestHeader(HEADER_STRING) final String token,
            @PathVariable final String id) {
        return this.therapistPatientGameService.getTherapies(token, id);
    }

    @PatchMapping(path = "/{id}/patients/{patientId}/{status}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public TherapyEntity changeTherapyStatus (@Valid @RequestHeader(HEADER_STRING) final String token,
            @PathVariable final Long id,
            @PathVariable final String patientId,
            @PathVariable final String status) {
        return this.therapistPatientGameService.changeTherapyStatus(token, patientId, id, status);
    }
}
