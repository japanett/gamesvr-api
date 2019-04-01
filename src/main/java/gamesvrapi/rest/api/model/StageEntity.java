package gamesvrapi.rest.api.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import gamesvrapi.rest.api.enums.PatientHandEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private PatientHandEnum handEnum;

}
