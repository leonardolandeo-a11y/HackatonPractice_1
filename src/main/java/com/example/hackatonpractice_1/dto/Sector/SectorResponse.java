package com.example.hackatonpractice_1.dto.Sector;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
@Getter
@Setter
public class SectorResponse {
    private Long id;
    private String sectorCode;
    private String climate;
    private Integer capacity;
    private Integer currentLoad;
    private Integer stabilityLevel;
    private Instant createdAt;

    protected SectorResponse(){}
}
