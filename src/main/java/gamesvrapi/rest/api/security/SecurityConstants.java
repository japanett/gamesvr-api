package gamesvrapi.rest.api.security;

public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 86_400; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_THERAPIST = "/api/therapist/session";
    public static final String SIGN_UP_ADMIN = "/api/admin/session";
}
