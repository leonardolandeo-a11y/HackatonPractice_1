package com.example.hackatonpractice_1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "notification_log")
public class NotificationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "signal_id", nullable = false)
    private TropelSignal signal;

    @Column(nullable = false)
    private String recipientEmail;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String notifStatus;

    @Column(columnDefinition = "TEXT")
    private String errorMessage;

    private Instant sentAt;

    @Column(nullable = false)
    private Instant createdAt;

    protected NotificationLog() {
    }

    public NotificationLog(
            TropelSignal signal,
            String recipientEmail,
            String subject,
            String notifStatus,
            String errorMessage,
            Instant sentAt,
            Instant createdAt
    ) {
        this.signal = signal;
        this.recipientEmail = recipientEmail;
        this.subject = subject;
        this.notifStatus = notifStatus;
        this.errorMessage = errorMessage;
        this.sentAt = sentAt;
        this.createdAt = createdAt;
    }
}