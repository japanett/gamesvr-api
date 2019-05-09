package gamesvrapi.rest.api.service;

import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import gamesvrapi.rest.api.dto.PacientPerformanceDTO;
import gamesvrapi.rest.api.entities.PacientEntity;
import gamesvrapi.rest.api.entities.TherapistEntity;
import gamesvrapi.rest.api.exceptions.ResourceNotFoundException;
import gamesvrapi.rest.api.repository.PacientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PacientService {

  @Autowired
  private final TokenInterceptorService tokenInterceptorService;

  @Autowired
  private final PacientRepository pacientRepository;

  @Transactional
  public PacientEntity create(final String token, final PacientEntity patient) {

    TherapistEntity therapist = tokenInterceptorService.translateTherapistToken(token);

    patient.setTherapist(therapist);
    patient.setId(generateId());
    log.info("============== PATIENT CREATED ============== ");
    log.info("Name: {}, Id: {}", patient.getName(), patient.getId());
    return pacientRepository.save(patient);
  }

  @Transactional
  public PacientEntity update(final String token, final String id, final PacientEntity patient) {

    TherapistEntity therapist = tokenInterceptorService.translateTherapistToken(token);

    PacientEntity updatedPatient = pacientRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Patient not found!"));

    updatedPatient.setAge(patient.getAge());
    updatedPatient.setDominantHand(patient.getDominantHand());
    updatedPatient.setGmfcsLevel(patient.getGmfcsLevel());
    updatedPatient.setName(patient.getName());
    updatedPatient.setObjective(patient.getObjective());
    updatedPatient.setPatology(patient.getPatology());
    updatedPatient.setSex(patient.getSex());

    return pacientRepository.save(updatedPatient);
  }

  // TODO Check if this will destroy the therapies sessions
  @Transactional
  public PacientEntity delete(String token, String id) {
    TherapistEntity therapist = tokenInterceptorService.translateTherapistToken(token);

    PacientEntity patient = pacientRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Patient not found!"));

    pacientRepository.delete(patient);
    return patient;
  }

  public List<PacientEntity> getPatientsByFilter(final String token, final String patientId) {
    TherapistEntity therapist = tokenInterceptorService.translateTherapistToken(token);

    var entity = PacientEntity.builder().id(patientId).therapist(therapist).build();

    List<PacientEntity> patientList = pacientRepository.findAll(Example.of(entity));

    if (patientList.isEmpty()) {
      throw new ResourceNotFoundException("No patient found!");
    }

    return patientList;
  }

  private String generateId() {
    String id = (UUID.randomUUID().toString().substring(0, 6));
    pacientRepository.findById(id).ifPresent(entity -> {
      this.generateId();
    });
    return id;
  }

  public List<PacientPerformanceDTO> getPatientPerformance(@Valid String token, String id) {
    List<PacientEntity> patients = getPatientsByFilter(token, id);
    return null;

  }
}
