package com.example.hackatonpractice_1.dto.NotificationLog;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class NotificationLogDTO {

    private Long id;
    private Long signalId;
    private String recipientEmail;
    private String subject;
    private String notifStatus;
    private String errorMessage;
    private Instant sentAt;
    private Instant createdAt;

    protected NotificationLogDTO() {
    }
}