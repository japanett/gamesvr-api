package gamesvrapi.rest.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class EncodeTokenFailedException extends RuntimeException {
        public EncodeTokenFailedException(final String message) {
            super(String.format(message));
    }
}
