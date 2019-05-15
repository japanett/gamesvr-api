package gamesvrapi.rest.api.controllers;

import static gamesvrapi.rest.api.security.SecurityConstants.HEADER_STRING;
import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;

import javax.validation.Valid;

import gamesvrapi.rest.api.entities.StageEntity;
import gamesvrapi.rest.api.entities.TherapyEntity;
import gamesvrapi.rest.api.service.TherapyService;
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
@RequestMapping("/api/therapy")
public class TherapyController {

    @Autowired
    private final TherapyService therapyService;

    @PostMapping(path = "/patient/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(CREATED)
    public TherapyEntity create (@Valid @RequestHeader(HEADER_STRING) final String token,
            @PathVariable final String id,
            @RequestBody final TherapyEntity request) {
        return this.therapyService.create(token, id, request);
    }

    @GetMapping(path = "/patient/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<TherapyEntity> getPatientTherapies (@Valid @RequestHeader(HEADER_STRING) final String token,
            @PathVariable final String id) {
        return this.therapyService.getPatientTherapies(token, id);
    }

    @PatchMapping(path = "/{id}/patient/{patientId}/{status}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public TherapyEntity changeTherapyStatus (@Valid @RequestHeader(HEADER_STRING) final String token,
            @PathVariable final Long id,
            @PathVariable final String patientId,
            @PathVariable final String status) {
        return this.therapyService.changeTherapyStatus(token, patientId, id, status);
    }

    @PostMapping(path = "/{id}/patient/{patientId}/stage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public TherapyEntity setStage (@Valid @RequestHeader(HEADER_STRING) final String token,
            @PathVariable final Long id,
            @PathVariable final String patientId,
            @RequestBody final StageEntity stage) {
        return this.therapyService.setStage(token, patientId, id, stage);
    }

    // TODO delete stage

}
