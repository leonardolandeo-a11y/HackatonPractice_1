package com.example.hackatonpractice_1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "sector")
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String sectorCode;

    @Column(nullable = false)
    private String climate;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private Integer currentLoad;

    @Column(nullable = false)
    private Integer stabilityLevel;

    @Column(nullable = false)
    private Instant createdAt;

    @OneToMany(mappedBy = "sector")
    private List<Tropel> tropels = new ArrayList<>();

    protected Sector() {}
}
