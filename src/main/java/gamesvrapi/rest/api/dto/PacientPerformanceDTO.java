package gamesvrapi.rest.api.dto;

import static javax.persistence.EnumType.STRING;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import gamesvrapi.rest.api.entities.TherapistEntity;
import gamesvrapi.rest.api.entities.TherapyEntity;
import gamesvrapi.rest.api.enums.PacientHandEnum;
import gamesvrapi.rest.api.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
