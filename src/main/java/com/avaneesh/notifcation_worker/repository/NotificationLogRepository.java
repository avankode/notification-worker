package com.avaneesh.notifcation_worker.repository;

import com.avaneesh.notifcation_worker.entity.NotificationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationLogRepository extends JpaRepository<NotificationLog, Long> {

}