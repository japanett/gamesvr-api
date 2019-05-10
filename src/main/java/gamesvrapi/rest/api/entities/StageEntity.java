package gamesvrapi.rest.api.entities;

import gamesvrapi.rest.api.enums.PacientHandEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stage")
@Entity(name = "Stage")
public class StageEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id", updatable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "THERAPY_ID")
  private TherapyEntity therapy;

  @Column(name = "step", nullable = false)
  private Integer step;

  @Column(name = "time", nullable = false)
  private Integer time;

  @Column(name = "hand", nullable = false)
  private PacientHandEnum handEnum;
}
