package gamesvrapi.rest.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import gamesvrapi.rest.api.model.TherapistEntity;
import gamesvrapi.rest.api.service.TherapistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/therapist")
public class TherapistController {

    private final TherapistService service;

    @GetMapping
    public List<TherapistEntity> getAllTherapists() {
        return this.service.getAllTherapists();
    }

    @PostMapping
    public TherapistEntity createTherapist(@Valid @RequestBody final TherapistEntity therapist) {
        return this.service.createTherapist(therapist);
    }

    @GetMapping("/{id}")
    public TherapistEntity getTherapistById(@PathVariable("id") final Long id) {
        return this.service.getTherapistById(id);
    }

}
