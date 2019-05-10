package gamesvrapi.rest.api.service;

import gamesvrapi.rest.api.entities.TherapistEntity;
import gamesvrapi.rest.api.repository.TherapistRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class TherapistDetailsServiceImpl implements UserDetailsService {

  private TherapistRepository therapistRepository;

  public TherapistDetailsServiceImpl(TherapistRepository therapistRepository) {
    this.therapistRepository = therapistRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    TherapistEntity therapist = therapistRepository.findByUsername(username);
    if (therapist == null) {
      throw new UsernameNotFoundException(username);
    }
    return new User(therapist.getUsername(), therapist.getPassword(), emptyList());
  }
}
