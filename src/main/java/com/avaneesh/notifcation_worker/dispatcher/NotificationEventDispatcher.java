package com.avaneesh.notifcation_worker.dispatcher;

import com.avaneesh.notifcation_worker.event.NotificationProcessedEvent;
import com.avaneesh.notifcation_worker.listener.NotificationListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NotificationEventDispatcher {

    private final List<NotificationListener> listeners;

    public void dispatch(NotificationProcessedEvent event) {
        for (NotificationListener listener : listeners) {
            listener.onNotificationProcessed(event);
        }
    }
}
