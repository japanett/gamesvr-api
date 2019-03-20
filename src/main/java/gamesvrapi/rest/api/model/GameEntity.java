package gamesvrapi.rest.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "game")
@Entity(name = "Game")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "gameIdentifier", nullable = false, unique = true)
    private Integer gameIdentifier;

    @Column(name = "imersive", nullable = false)
    private Boolean imersive;

//  If game can be seen by therapist
    @Column(name = "available", nullable = false)
    private Boolean available;

    @OneToMany(mappedBy = "stages", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StageEntity> stages;

    @PrePersist
    public void preInsert() {
        if (this.imersive == null) {
            this.setImersive(Boolean.TRUE);
        }
        if (this.available == null) {
            this.setAvailable(Boolean.TRUE);
        }
    }
}
