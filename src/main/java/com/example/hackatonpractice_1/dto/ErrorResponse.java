package com.example.hackatonpractice_1.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter

public class ErrorResponse {
    private String error;
    private String message;
    private Instant timestamp;
    private String path;

    public ErrorResponse(String error, String message, Instant timestamp, String path) {
        this.error = error;
        this.message = message;
        this.timestamp = timestamp;
        this.path = path;
    }
}
