package com.example.hackatonpractice_1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "care_response")
public class CareResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "signal_id", nullable = false, unique = true)
    private TropelSignal signal;

    @Column(nullable = false)
    private String responseCode;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    private Instant createdAt;

    protected CareResponse() {
    }

    public CareResponse(
            TropelSignal signal,
            String responseCode,
            String description,
            Instant createdAt
    ) {
        this.signal = signal;
        this.responseCode = responseCode;
        this.description = description;
        this.createdAt = createdAt;
    }
}