package gamesvrapi.rest.api.security;

import com.auth0.jwt.JWT;

import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static gamesvrapi.rest.api.security.SecurityConstants.EXPIRATION_TIME;
import static gamesvrapi.rest.api.security.SecurityConstants.SECRET;

public class JWTService {

    public static String generateTherapistToken(final long id) {
        return JWT.create()
                .withSubject(Long.toString(id))
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
    }

}
