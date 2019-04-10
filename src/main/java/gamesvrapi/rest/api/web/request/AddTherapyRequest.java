package gamesvrapi.rest.api.web.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gamesvrapi.rest.api.enums.PlatformEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddTherapyRequest {

    @NotNull(message = "FIELD_BLANK")
    private String name;

    @NotNull(message = "FIELD_BLANK")
    private PlatformEnum platform;

    @NotNull(message = "FIELD_BLANK")
    private String patientId;



    @NotNull(message = "FIELD_BLANK")
    private List<PlatformEnum> platforms;

}
