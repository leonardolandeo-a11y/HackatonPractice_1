package Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="tropelsignals")
public class TropelSignal {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id Long id;

    @ManyToOne
    @JoinColumn(name="trope_id",nullable = false)
    private Tropel tropel;

    @ManyToOne
    @JoinColumn(name="guardian_id",nullable = false)
    private Guardian guardian;

    String senderTag;

    @Column(columnDefinition = "TEXT")
    String rawContent;

    String signalType;
    String severity;
    String assignedUnit;

    @Column(columnDefinition = "TEXT")
    String recommendedAction;
    String status;
    Instant createdAt;
    Instant updatedAt;


    public TropelSignal() {
    }


    public TropelSignal(Tropel tropel, Guardian guardian, String senderTag, String rawContent) {
        this.tropel = tropel;
        this.guardian = guardian;
        this.senderTag = senderTag;
        this.rawContent = rawContent;
    }
}
