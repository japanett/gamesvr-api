package gamesvrapi.rest.api.entities;

import static javax.persistence.EnumType.STRING;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gamesvrapi.rest.api.enums.PatientHandEnum;
import gamesvrapi.rest.api.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "patient")
@Entity(name = "Patient")
public class PatientEntity {

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
    private PatientHandEnum dominantHand;

    @Column(name = "patology", nullable = false)
    private String patology;

    @Column(name = "objective", nullable = false)
    private String objective;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TherapyEntity> therapies;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @PrePersist
    public void preInsert () {
        if (this.gmfcsLevel == null) {
            this.setGmfcsLevel(1);
        }
        if (this.dominantHand == null) {
            this.setDominantHand(PatientHandEnum.RIGHT);
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

}
