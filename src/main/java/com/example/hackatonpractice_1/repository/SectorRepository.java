package com.example.hackatonpractice_1.repository;

import com.example.hackatonpractice_1.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SectorRepository extends JpaRepository<Sector, Long> {
    Optional<Sector> findBySectorCode(String sectorCode);
    boolean existsBySectorCode(String sectorCode);
}

