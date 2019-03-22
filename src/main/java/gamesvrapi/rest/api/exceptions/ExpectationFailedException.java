package gamesvrapi.rest.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
public class ExpectationFailedException extends RuntimeException {
    public ExpectationFailedException(final String message) {
        super(String.format(message));
    }
}