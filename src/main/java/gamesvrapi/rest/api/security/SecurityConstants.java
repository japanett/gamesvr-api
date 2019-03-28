package gamesvrapi.rest.api.security;

public class SecurityConstants {

    public static final String SECRET = "SecretKeyToGenJWTs";

    public static final long EXPIRATION_TIME = 8_640_000; // 1 day

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING = "japanetToken";

    public static final String CREATE_THERAPIST = "/api/therapist";
}