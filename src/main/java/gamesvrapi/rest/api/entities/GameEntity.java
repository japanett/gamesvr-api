package gamesvrapi.rest.api.entities;

import gamesvrapi.rest.api.enums.PlatformEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "game")
@Entity(name = "Game")
public class GameEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id", updatable = false)
  private Long id;

  @CreatedDate
  @Column(name = "dat_creation", nullable = false, updatable = false)
  private LocalDateTime creationDate;

  @LastModifiedDate
  @Column(name = "dat_update", nullable = false)
  private LocalDateTime updateDate;

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @Column(name = "createdBy", nullable = false)
  private String createdBy;

  @ElementCollection(targetClass = PlatformEnum.class)
  @Enumerated(STRING)
  @JoinColumn(name = "platforms")
  private List<PlatformEnum> platforms;
}
