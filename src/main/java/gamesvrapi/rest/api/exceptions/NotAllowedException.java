package gamesvrapi.rest.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class NotAllowedException extends RuntimeException {

    public NotAllowedException (final String message) {
        super(String.format(message));
    }
}
