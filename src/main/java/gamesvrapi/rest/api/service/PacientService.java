package gamesvrapi.rest.api.service;

import gamesvrapi.rest.api.dto.PacientPerformanceDTO;
import gamesvrapi.rest.api.entities.PacientEntity;
import gamesvrapi.rest.api.entities.TherapistEntity;
import gamesvrapi.rest.api.exceptions.ResourceNotFoundException;
import gamesvrapi.rest.api.repository.PacientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PacientService {

  @Autowired private final TokenInterceptorService tokenInterceptorService;

  @Autowired private final PacientRepository pacientRepository;

  @Transactional
  public PacientEntity create(final String token, final PacientEntity Pacient) {

    TherapistEntity therapist = tokenInterceptorService.translateTherapistToken(token);

    Pacient.setTherapist(therapist);
    Pacient.setId(generateId());
    log.info("============== Pacient CREATED ============== ");
    log.info("Name: {}, Id: {}", Pacient.getName(), Pacient.getId());
    return pacientRepository.save(Pacient);
  }

  @Transactional
  public PacientEntity update(final String token, final String id, final PacientEntity Pacient) {

    TherapistEntity therapist = tokenInterceptorService.translateTherapistToken(token);

    PacientEntity updatedPacient =
        pacientRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Pacient not found!"));

    updatedPacient.setAge(Pacient.getAge());
    updatedPacient.setDominantHand(Pacient.getDominantHand());
    updatedPacient.setGmfcsLevel(Pacient.getGmfcsLevel());
    updatedPacient.setName(Pacient.getName());
    updatedPacient.setObjective(Pacient.getObjective());
    updatedPacient.setPatology(Pacient.getPatology());
    updatedPacient.setSex(Pacient.getSex());

    return pacientRepository.save(updatedPacient);
  }

  // TODO Check if this will destroy the therapies sessions
  @Transactional
  public PacientEntity delete(String token, String id) {
    TherapistEntity therapist = tokenInterceptorService.translateTherapistToken(token);

    PacientEntity Pacient =
        pacientRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Pacient not found!"));

    pacientRepository.delete(Pacient);
    return Pacient;
  }

  public List<PacientEntity> getPacientsByFilter(final String token, final String PacientId) {
    TherapistEntity therapist = tokenInterceptorService.translateTherapistToken(token);

    var entity = PacientEntity.builder().id(PacientId).therapist(therapist).build();

    List<PacientEntity> PacientList = pacientRepository.findAll(Example.of(entity));

    if (PacientList.isEmpty()) {
      throw new ResourceNotFoundException("No Pacient found!");
    }

    return PacientList;
  }

  private String generateId() {
    String id = (UUID.randomUUID().toString().substring(0, 6));
    pacientRepository
        .findById(id)
        .ifPresent(
            entity -> {
              this.generateId();
            });
    return id;
  }

  public List<PacientPerformanceDTO> getPacientPerformance(@Valid String token, String id) {
    List<PacientEntity> Pacients = getPacientsByFilter(token, id);
    return new ArrayList<>();
  }
}
