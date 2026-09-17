package com.example.hackatonpractice_1.repository;

import com.example.hackatonpractice_1.model.NotificationLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationLogRepository extends JpaRepository<NotificationLog, Long> {

    List<NotificationLog> findBySignalId(Long signalId);
}