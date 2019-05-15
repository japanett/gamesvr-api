package gamesvrapi.rest.api.service;

import java.util.List;
import java.util.UUID;

import gamesvrapi.rest.api.entities.PatientEntity;
import gamesvrapi.rest.api.entities.TherapistEntity;
import gamesvrapi.rest.api.exceptions.ResourceNotFoundException;
import gamesvrapi.rest.api.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientService {

    @Autowired
    private final TokenInterceptorService tokenInterceptorService;

    @Autowired
    private final PatientRepository patientRepository;

    @Transactional
    public PatientEntity create (final String token, final PatientEntity patient) {

        TherapistEntity therapist = tokenInterceptorService.translateTherapistToken(token);

        patient.setTherapist(therapist);
        patient.setId(generateId());
        log.info("============== PATIENT CREATED ============== ");
        log.info("Name: {}, Id: {}", patient.getName(), patient.getId());
        return patientRepository.save(patient);
    }

    @Transactional
    public PatientEntity update (final String token, final String id,
            final PatientEntity patient) {

        TherapistEntity therapist = tokenInterceptorService.translateTherapistToken(token);

        PatientEntity updatedPatient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found!"));

        updatedPatient.setAge(patient.getAge());
        updatedPatient.setDominantHand(patient.getDominantHand());
        updatedPatient.setGmfcsLevel(patient.getGmfcsLevel());
        updatedPatient.setName(patient.getName());
        updatedPatient.setObjective(patient.getObjective());
        updatedPatient.setPatology(patient.getPatology());
        updatedPatient.setSex(patient.getSex());

        return patientRepository.save(updatedPatient);
    }

    // TODO Check if this will destroy the therapies sessions
    @Transactional
    public PatientEntity delete (String token, String id) {
        TherapistEntity therapist = tokenInterceptorService.translateTherapistToken(token);

        PatientEntity patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found!"));

        patientRepository.delete(patient);
        return patient;
    }

    public List<PatientEntity> getPatientsByFilter (final String token, final String patientId) {
        TherapistEntity therapist = tokenInterceptorService.translateTherapistToken(token);
//        log.info("therapist: {}", therapist);
        log.info("===========");
        log.info(therapist.getEmail());
        PatientEntity entity = PatientEntity.builder()
                .id(patientId)
                .therapist(therapist)
                .build();

        List<PatientEntity> patientList = patientRepository.findAll(Example.of(entity));
        // TODO ADiciona um breakpoint aqui e ve o pq o erro ta estorando no terapist
        //        Optional<List<TherapyEntity>> patientList2 = patientRepository.findAll(Example.of(entity))
        //                .stream()
        //                .findFirst()
        //                .filter(x -> !x.getTherapies().isEmpty())
        //                .map(x ->  x.getTherapies());

        if (patientList.isEmpty()) {
            throw new ResourceNotFoundException("No patient found!");
        }

        return patientList;
    }

    private String generateId () {
        String id = (UUID.randomUUID().toString().substring(0, 6));
        patientRepository.findById(id)
                .ifPresent(entity -> {
                    this.generateId();
                });
        return id;
    }
}
