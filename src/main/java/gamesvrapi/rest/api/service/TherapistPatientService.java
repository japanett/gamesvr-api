package gamesvrapi.rest.api.service;

import gamesvrapi.rest.api.model.PatientEntity;
import gamesvrapi.rest.api.model.TherapistEntity;
import gamesvrapi.rest.api.repository.Patient.PatientRepository;
import gamesvrapi.rest.api.repository.Therapist.TherapistPatientEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TherapistPatientService {

    @Autowired
    private final PatientRepository patientRepository;

    @Autowired
    private final TokenInterceptorService tokenInterceptorService;

    @Autowired
    private final TherapistPatientEntityRepository therapistPatientEntityRepository;

    // Do a method to check if the Id already exists, if it does, generate another one and THEN save the patient
    @Transactional
    public PatientEntity createPatient (final String token, final PatientEntity patient) {
        TherapistEntity therapist = tokenInterceptorService.translateTherapistToken(token);
        patient.setTherapist(therapist);
        patient.setId("d9fc91");
        patient.setActive(Boolean.TRUE);
        return patientRepository.save(patient);
    }

}
