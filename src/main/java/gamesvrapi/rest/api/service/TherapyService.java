package gamesvrapi.rest.api.service;

import gamesvrapi.rest.api.entities.PacientEntity;
import gamesvrapi.rest.api.entities.TherapistEntity;
import gamesvrapi.rest.api.entities.TherapyEntity;
import gamesvrapi.rest.api.enums.PlatformEnum;
import gamesvrapi.rest.api.exceptions.DuplicateEntryException;
import gamesvrapi.rest.api.exceptions.ExpectationFailedException;
import gamesvrapi.rest.api.exceptions.ResourceNotFoundException;
import gamesvrapi.rest.api.repository.PacientRepository;
import gamesvrapi.rest.api.repository.TherapyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TherapyService {

  @Autowired private final TokenInterceptorService tokenInterceptorService;

  @Autowired private final PacientRepository pacientRepository;

  @Autowired private final TherapyRepository therapyRepository;

  @Transactional
  public TherapyEntity create(
      final String token, final String pacientId, final TherapyEntity request) {

    TherapistEntity therapist = tokenInterceptorService.translateTherapistToken(token);

    PacientEntity pacient =
        pacientRepository
            .findById(pacientId)
            .orElseThrow(() -> new ResourceNotFoundException("pacient not found!"));

    this.checkDuplicateTherapy(pacient.getTherapies(), request.getName(), request.getPlatform());

    request.setPacient(pacient);
    log.info(
        "=============== CREATED THERAPY:{0}, PLATFORM:{1}, pacient: {2} ===============",
        request.getName(), request.getPlatform().getDescription(), pacient.getName());

    return therapyRepository.save(request);
  }

  public List<TherapyEntity> getPacientTherapies(final String token, final String pacientId) {
    TherapistEntity therapist = tokenInterceptorService.translateTherapistToken(token);

    return therapyRepository
        .findByPacientId(pacientId)
        .orElseThrow(() -> new ResourceNotFoundException("Therapies not found!"));
  }

  @Transactional
  public TherapyEntity changeTherapyStatus(
      final String token, final String pacientId, final Long therapyId, String status) {
    TherapistEntity therapist = tokenInterceptorService.translateTherapistToken(token);

    TherapyEntity therapy =
        therapyRepository
            .findByPacientIdAndId(pacientId, therapyId)
            .orElseThrow(() -> new ResourceNotFoundException("Therapy not found for this pacient"));

    switch (status) {
      case "activate":
        therapy.setActive(Boolean.TRUE);
        return therapyRepository.save(therapy);
      case "deactivate":
        therapy.setActive(Boolean.FALSE);
        return therapyRepository.save(therapy);
      default:
        throw new ExpectationFailedException("activate / deactivate expected");
    }
  }

  // Check if "Name" && "Platform" && "Active=True" already exists, if it does, throw error for
  // duplicate entry
  private void checkDuplicateTherapy(
      List<TherapyEntity> therapies, String name, PlatformEnum platform) {
    therapies.forEach(
        therapy -> {
          if (therapy.getName().equals(name)
              && therapy.getPlatform() == platform
              && therapy.getActive() == Boolean.TRUE) {
            log.warn("TherapyService, m=addTherapy, e=Duplicate Therapy entry");
            throw new DuplicateEntryException("Therapy already exists!");
          }
        });
  }
}
