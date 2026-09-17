package com.example.hackatonpractice_1.dto.Sector;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSectorRequest {
    @NotBlank
    private String sectorCode;

     @NotBlank
    private String climate;
     @NotBlank
     @Positive
    private Integer capacity;
     protected CreateSectorRequest(){}
}
