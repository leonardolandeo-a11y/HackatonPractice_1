package com.example.hackatonpractice_1.exception;



import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
@Getter
@Setter
public class ErrorResponse {
    private String error;
    private String message;
    private Instant timeStamp;
    private String path;

    public ErrorResponse(String error, String message, Instant timeStamp, String path) {
        this.error = error;
        this.message = message;
        this.timeStamp = timeStamp;
        this.path = path;
    }

    protected ErrorResponse(){}
}
