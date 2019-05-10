package gamesvrapi.rest.api.web.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatchTherapistRequest {

  @NotNull(message = "FIELD_BLANK")
  private String name;

  @NotNull(message = "FIELD_BLANK")
  private String email;
}
