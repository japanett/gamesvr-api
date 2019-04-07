package gamesvrapi.rest.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamesvrapi.rest.api.exceptions.DuplicateEntryException;
import gamesvrapi.rest.api.model.TherapistEntity;
import gamesvrapi.rest.api.repository.Therapist.TherapistRepository;
import gamesvrapi.rest.api.web.request.PatchTherapistRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TherapistService {

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private final TokenInterceptorService tokenInterceptorService;

    @Autowired
    private final TherapistRepository therapistRepository;

    public TherapistEntity createTherapist (final TherapistEntity therapist) {
        try {
            log.info("============== USER CREATED ==============");
            log.info("{}", therapist);
            therapist.setPassword(this.bCryptPasswordEncoder.encode(therapist.getPassword()));
            return therapistRepository.save(therapist);
        } catch (DataIntegrityViolationException exception) {
            throw new DuplicateEntryException("Therapist Service, m=createTherapist, e=Duplicate username or email");
        }
    }

    public TherapistEntity getTherapist (final String token) {
        return tokenInterceptorService.translateTherapistToken(token);
    }

    @Transactional
    public TherapistEntity deleteTherapist (final String token) {
        TherapistEntity therapist = tokenInterceptorService.translateTherapistToken(token);
        therapistRepository.delete(therapist);
        return therapist;
    }

    @Transactional
    public TherapistEntity patchTherapist (final String token,
            final PatchTherapistRequest req) {
        TherapistEntity therapist = tokenInterceptorService.translateTherapistToken(token);
        therapist.setName(req.getName());
        therapist.setEmail(req.getEmail());
        return therapistRepository.save(therapist);
    }

}
