package gamesvrapi.rest.api.mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;
import gamesvrapi.rest.api.dto.TokenDTO;
import gamesvrapi.rest.api.web.response.AuthenticationResponse;
import org.mapstruct.Mapper;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface AuthenticationMapper {

  // TODO - implementar autenticador
  AuthenticationResponse toResponse(TokenDTO token);
}
