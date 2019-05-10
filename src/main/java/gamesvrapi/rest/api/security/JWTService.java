package gamesvrapi.rest.api.security;

import static com.auth0.jwt.JWT.decode;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static gamesvrapi.rest.api.security.SecurityConstants.EXPIRATION_TIME;
import static gamesvrapi.rest.api.security.SecurityConstants.SECRET;
import java.util.Date;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import gamesvrapi.rest.api.dto.PayloadDTO;
import gamesvrapi.rest.api.exceptions.EncodeTokenFailedException;
import gamesvrapi.rest.api.exceptions.ExpiredTokenException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTService {

  public static String generateToken(final long id, final String role) {
    try {
      return JWT.create().withSubject(String.format("%d;%s", id, role))
          .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
          .sign(HMAC512(SECRET.getBytes()));
    } catch (JWTCreationException exception) {
      log.warn("JWTService, m=generateTherapistToken, e: error generating token!");
      throw new EncodeTokenFailedException("Error generating token!");
    }
  }

  // Do another "generateToken" method for /pacients/session

  public static PayloadDTO getTokenPayload(final String token) {
    try {
      if (new Date().after(decode(token).getExpiresAt())) {
        log.warn("JWTService, m=getIdFromToken, e: token expired!");
        throw new ExpiredTokenException("Token expired!");
      }
      String tokenSubject = JWT.decode(token).getSubject();

      return PayloadDTO.builder().id(tokenSubject.split(";")[0]).role(tokenSubject.split(";")[1])
          .build();

    } catch (JWTDecodeException exception) {
      log.warn("JWTService, m=getIdFromToken, e: error decoding token!");
      throw new EncodeTokenFailedException("Error decoding token!");
    }
  }

}
