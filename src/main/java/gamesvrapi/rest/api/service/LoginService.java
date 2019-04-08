package gamesvrapi.rest.api.service;

import gamesvrapi.rest.api.dto.TokenDTO;
import gamesvrapi.rest.api.exceptions.ExpectationFailedException;
import gamesvrapi.rest.api.exceptions.ResourceNotFoundException;
import gamesvrapi.rest.api.entities.AdminEntity;
import gamesvrapi.rest.api.entities.TherapistEntity;
import gamesvrapi.rest.api.repository.Admin.AdminRepository;
import gamesvrapi.rest.api.repository.Therapist.TherapistRepository;
import gamesvrapi.rest.api.security.JWTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private final TherapistRepository therapistRepository;

    @Autowired
    private final AdminRepository adminRepository;

    public TokenDTO loginTherapist (final String username, final String password) {
        TherapistEntity therapist = therapistRepository.findByUsername(username);
        try {
            if (!this.bCryptPasswordEncoder.matches(password, therapist.getPassword())) {
                throw new ExpectationFailedException("Incorrect password, username={" + username + "}");
            }
        } catch (java.lang.NullPointerException exception) {
            throw new ResourceNotFoundException("Therapist username={" + username + "} not found!");
        }
        return TokenDTO.builder()
                .japanetToken(JWTService.generateToken(therapist.getId()))
                .build();
    }

    public TokenDTO loginAdmin (final String username, final String password) {
        AdminEntity admin = adminRepository.findByUsername(username);
        try {
            if (!this.bCryptPasswordEncoder.matches(password, admin.getPassword())) {
                throw new ExpectationFailedException("Incorrect password, username={" + username + "}");
            }
        } catch (java.lang.NullPointerException exception) {
            throw new ResourceNotFoundException("Admin username={" + username + "} not found!");
        }
        return TokenDTO.builder()
                .japanetToken(JWTService.generateToken(admin.getId()))
                .build();
    }

}
