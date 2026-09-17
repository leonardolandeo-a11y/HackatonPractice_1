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
@Table(name ="guardians")
public class Guardian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String displayName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String notificationEmail;

    @Column(nullable = false)
    private Instant createdAt;

    @OneToMany(mappedBy = "guardian")
    private List<Tropel> tropels = new ArrayList<>();

    protected Guardian() {}
}
