package DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class TropelResponseDTO {
    private Long id;
    private String name;
    private String species;
    private String vitalState;
    private Integer energyLevel;
    private Integer chaosIndex;
    private Integer mutationStage;

    // Datos aplanados del Sector
    private Long sectorId;
    private String sectorCode;

    // Datos aplanados del Guardian
    private Long guardianId;
    private String guardianName;

    private Instant createdAt;
    private Instant updatedAt;
}
