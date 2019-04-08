package gamesvrapi.rest.api.controllers;

import static gamesvrapi.rest.api.security.SecurityConstants.HEADER_STRING;

import java.util.List;

import javax.validation.Valid;

import gamesvrapi.rest.api.entities.GameEntity;
import gamesvrapi.rest.api.entities.PatientEntity;
import gamesvrapi.rest.api.entities.TherapistEntity;
import gamesvrapi.rest.api.service.TherapistPatientGameService;
import gamesvrapi.rest.api.service.TherapistPatientService;
import gamesvrapi.rest.api.service.TherapistService;
import gamesvrapi.rest.api.web.request.AddGameRequest;
import gamesvrapi.rest.api.web.request.PatchTherapistRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/therapist")
public class TherapistController {

    @Autowired
    private final TherapistService therapistService;

    @Autowired
    private final TherapistPatientService therapistPatientService;

    @Autowired
    private final TherapistPatientGameService therapistPatientGameService;

    // Therapist CRUD
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public TherapistEntity createTherapist (@Valid @RequestBody @NonNull final TherapistEntity therapist) {
        return this.therapistService.createTherapist(therapist);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public TherapistEntity getTherapist (@Valid @RequestHeader(HEADER_STRING) final String token) {
        return this.therapistService.getTherapist(token);
    }

    @PatchMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public TherapistEntity patchTherapist (@Valid @RequestHeader(HEADER_STRING) final String token,
            @Valid @RequestBody final PatchTherapistRequest req) {
        return this.therapistService.patchTherapist(token, req);
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public TherapistEntity deleteTherapist (@Valid @RequestHeader(HEADER_STRING) final String token) {
        return this.therapistService.deleteTherapist(token);
    }

    // Patients CRUD
    @PostMapping(path = "/patient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PatientEntity createPatient (@Valid @RequestHeader(HEADER_STRING) final String token,
            @Valid @RequestBody @NonNull final PatientEntity patient) {
        return this.therapistPatientService.createPatient(token, patient);
    }

    @GetMapping(path = "/patients", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<PatientEntity> getPatients (@Valid @RequestHeader(HEADER_STRING) final String token) {
        return this.therapistPatientService.getPatients(token);
    }

    @GetMapping(path = "/patient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PatientEntity getPatient (@Valid @RequestHeader(HEADER_STRING) final String token,
            @RequestParam @NonNull final String patientId) {
        return this.therapistPatientService.getPatient(token, patientId);
    }

    @PutMapping(path = "/patient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PatientEntity putPatient (@Valid @RequestHeader(HEADER_STRING) final String token,
            @RequestParam @NonNull final String patientId,
            @RequestBody @NonNull final PatientEntity patient) {
        return this.therapistPatientService.updatePatient(token, patientId, patient);
    }

    @DeleteMapping(path = "/patient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PatientEntity deletePatient (@Valid @RequestHeader(HEADER_STRING) final String token,
                                     @RequestParam @NonNull final String patientId) {
        return this.therapistPatientService.deletePatient(token, patientId);
    }

    // Games
    @GetMapping(path = "/games", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<GameEntity> getGames (@Valid @RequestHeader(HEADER_STRING) final String token) {
        return this.therapistPatientGameService.getAvailableGames(token);
    }

    @PostMapping(path = "/{patientId}/game", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PatientEntity addGame (@Valid @RequestHeader(HEADER_STRING) final String token,
            @PathVariable final String patientId,
            @RequestBody final AddGameRequest request) {
        return this.therapistPatientGameService.addGame(token, patientId, request);
    }

}
