package com.avaneesh.notifcation_worker.listener;

import com.avaneesh.notifcation_worker.event.NotificationProcessedEvent;

public interface NotificationListener {
    void onNotificationProcessed(NotificationProcessedEvent event);
}
