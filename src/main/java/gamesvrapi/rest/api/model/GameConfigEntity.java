package gamesvrapi.rest.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "gameConfig")
@Entity(name = "GameConfig")
public class GameConfigEntity {

    @Id
    @Column(name = "id", updatable = false)
    private Long id;
    @GeneratedValue(strategy = IDENTITY)

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="GAME_ID")
    private GameEntity game;

    @Column(name = "imersive", nullable = false)
    private Boolean imersive;

    @OneToMany(mappedBy = "gameConfig", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StageEntity> stages;

    @PrePersist
    public void preInsert() {
        if (this.imersive == null) {
            this.setImersive(Boolean.TRUE);
        }
    }
}
