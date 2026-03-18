package com.avaneesh.notifcation_worker.listener;

import com.avaneesh.notifcation_worker.dispatcher.NotificationEventDispatcher;
import com.avaneesh.notifcation_worker.event.NotificationProcessedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationEventHandler {

    private final NotificationEventDispatcher dispatcher;

    @EventListener
    public void handle(NotificationProcessedEvent event) {
        dispatcher.dispatch(event);
    }
}
