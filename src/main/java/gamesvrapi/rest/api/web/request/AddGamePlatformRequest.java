package gamesvrapi.rest.api.web.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gamesvrapi.rest.api.enums.PlatformEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddGamePlatformRequest {

  @NotNull(message = "FIELD_BLANK")
  private List<PlatformEnum> platforms;
}
