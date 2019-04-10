package gamesvrapi.rest.api.service;

import gamesvrapi.rest.api.exceptions.ResourceNotFoundException;
import gamesvrapi.rest.api.entities.AdminEntity;
import gamesvrapi.rest.api.entities.TherapistEntity;
import gamesvrapi.rest.api.repository.Admin.AdminRepository;
import gamesvrapi.rest.api.repository.Therapist.TherapistRepository;
import gamesvrapi.rest.api.security.JWTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenInterceptorService {

    @Autowired
    private final TherapistRepository therapistRepository;

    @Autowired
    private final AdminRepository adminRepository;

    public TherapistEntity translateTherapistToken (final String token) {
        long id = JWTService.getIdFromToken(token);
        return therapistRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("TokenInterceptor, m=translateTherapistToken, id : {}, e: ResourceNotFound", id);
                    return new ResourceNotFoundException("No therapist for this token");
                });
    }

    public AdminEntity translateAdminToken (final String token) {
        long id = JWTService.getIdFromToken(token);
        return adminRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("TokenInterceptor, m=translateAdminToken, id : {}, e: ResourceNotFound", id);
                    return new ResourceNotFoundException("No admin for this token");
                });
    }
}
