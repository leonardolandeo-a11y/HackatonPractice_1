package com.example.hackatonpractice_1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AiClassificationDTO {

    private String signalType;
    private String severity;
    private String assignedUnit;
    private String recommendedAction;

    public AiClassificationDTO() {}

    public AiClassificationDTO(String signalType, String severity, String assignedUnit, String recommendedAction) {
        this.signalType = signalType;
        this.severity = severity;
        this.assignedUnit = assignedUnit;
        this.recommendedAction = recommendedAction;
    }
}

