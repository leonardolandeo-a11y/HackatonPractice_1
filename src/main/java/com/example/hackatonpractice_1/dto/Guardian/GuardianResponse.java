package com.example.hackatonpractice_1.dto.Guardian;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
@Getter
@Setter 
public class GuardianResponse {
    private Long id;
    private String displayName;
    private String email;
    private String notificationEmail;
    private Instant createdAt;
    protected GuardianResponse(){}
}
