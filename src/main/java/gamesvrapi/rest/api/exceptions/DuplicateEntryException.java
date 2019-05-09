package gamesvrapi.rest.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateEntryException extends RuntimeException {

  public DuplicateEntryException(final String message) {
    super(String.format(message));
  }
}
