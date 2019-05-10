package gamesvrapi.rest.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gamesvrapi.rest.api.enums.PacientHandEnum;
import gamesvrapi.rest.api.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.EnumType.STRING;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "pacient")
@Entity(name = "Pacient")
public class PacientEntity {

  @Id
  @Column(name = "id", updatable = false)
  @Size(max = 6)
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "THERAPIST_ID", nullable = false)
  private TherapistEntity therapist;

  @CreatedDate
  @Column(name = "dat_creation", nullable = false, updatable = false)
  private LocalDateTime creationDate;

  @LastModifiedDate
  @Column(name = "dat_update", nullable = false)
  private LocalDateTime updateDate;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "age", nullable = false)
  private Integer age;

  @Column(name = "sex", nullable = false)
  @Enumerated(EnumType.STRING)
  private SexEnum sex;

  @Column(name = "gmfcsLevel")
  private Integer gmfcsLevel;

  @Enumerated(STRING)
  @Column(name = "dominantHand", nullable = false)
  private PacientHandEnum dominantHand;

  @Column(name = "patology", nullable = false)
  private String patology;

  @Column(name = "objective", nullable = false)
  private String objective;

  @OneToMany(mappedBy = "pacient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<TherapyEntity> therapies;

  @Column(name = "active", nullable = false)
  private Boolean active;

  @PrePersist
  public void preInsert() {
    if (this.gmfcsLevel == null) {
      this.setGmfcsLevel(1);
    }
    if (this.dominantHand == null) {
      this.setDominantHand(PacientHandEnum.RIGHT);
    }
    if (this.active == null) {
      this.setActive(Boolean.TRUE);
    }
  }

  @JsonIgnore
  public TherapistEntity getTherapist() {
    return therapist;
  }

  @JsonIgnore
  public void setTherapist(TherapistEntity therapist) {
    this.therapist = therapist;
  }

  @JsonIgnore
  public List<TherapyEntity> getTherapies() {
    return therapies;
  }
}
