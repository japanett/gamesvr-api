package gamesvrapi.rest.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class ExpiredTokenException extends RuntimeException {

    public ExpiredTokenException (final String message) {
        super(String.format(message));
    }
}