package gamesvrapi.rest.api.dto;

import gamesvrapi.rest.api.entities.TherapistEntity;
import gamesvrapi.rest.api.entities.TherapyEntity;
import gamesvrapi.rest.api.enums.PacientHandEnum;
import gamesvrapi.rest.api.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PacientPerformanceDTO {

  private String id;

  private TherapistEntity therapist;

  private LocalDateTime creationDate;

  private LocalDateTime updateDate;

  private String name;

  private Integer age;

  private SexEnum sex;

  private Integer gmfcsLevel;

  private PacientHandEnum dominantHand;

  private String patology;

  private String objective;

  private List<TherapyEntity> therapies;

  private Boolean active;
}
