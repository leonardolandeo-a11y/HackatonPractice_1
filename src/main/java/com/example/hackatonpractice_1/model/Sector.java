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

    private String climate;
    private Integer capacity;
    private Integer currentLoad;
    private Integer stabilityLevel;
    private Instant createdAt;

    @OneToMany(mappedBy = "sector")
    private List<Tropel> tropels = new ArrayList<>();

    protected Sector(){}
}
