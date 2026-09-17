package com.example.hackatonpractice_1.dto.CareResponse;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class CareResponseDTO {

    private Long id;
    private Long signalId;
    private String responseCode;
    private String description;
    private Instant createdAt;

    protected CareResponseDTO() {
    }
}