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
    protected ErrorResponse(){}
}
