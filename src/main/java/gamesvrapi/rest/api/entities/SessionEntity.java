package gamesvrapi.rest.api.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "session")
@Entity(name = "Session")
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "THERAPY_ID")
    private TherapyEntity therapy;

    @CreatedDate
    @Column(name = "dat_played", nullable = false, updatable = false)
    private LocalDateTime playedDate;

    @Column(name = "observation")
    private String observation;

    @Column(name = "elapsedTime")
    private Double elapsedTime;

    @Column(name = "rightHandScore")
    private Integer rightHandScore;

    @Column(name = "leftHandScore")
    private Integer leftHandScore;

    @Column(name = "alternatedHandScore")
    private Integer alternatedHandScore;

    @Column(name = "rightHandError")
    private Integer rightHandError;

    @Column(name = "leftHandError")
    private Integer leftHandError;

    @Column(name = "alternatedHandError")
    private Integer alternatedHandError;
}

