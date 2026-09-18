package Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="tropeles")
public class Tropel {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id Long id;

    @ManyToOne
    @JoinColumn(name="sector_id",nullable = false)
    private Sector sector;

    @ManyToOne
    @JoinColumn(name="guardian_id",nullable = false)
    private Guardian guardian;

    String name;
    String species;
    String vitalState="ESTABLE";
    Integer energyLevel=80;
    Integer chaosIndex=10;
    Integer mutationStage=0;

    Instant createdAt;
    Instant updatedAt;

    public Tropel(String name, String species, Sector sector, Guardian guardian) {
        this.name = name;
        this.species = species;
        this.sector = sector;
        this.guardian = guardian;
    }
}
