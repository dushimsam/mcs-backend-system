package com.example.mount_carmel_school.service.notification.processor;

import com.example.mount_carmel_school.dto.parent_dto.ParentDtoGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

@Service
public class ParentRegistrationNotificationProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ParentRegistrationNotificationProcessor.class);

    private List<Consumer<ParentDtoGet>> listeners = new CopyOnWriteArrayList< >();

    public void register(Consumer < ParentDtoGet > listener) {
        listeners.add(listener);
        LOGGER.info("Added a listener, for a total of {} listener{}", listeners.size(), listeners.size() > 1 ? "s" : "");
    }

    // TODO FBE implement unregister

    public void process(ParentDtoGet event) {
//        System.out.println("Processing: " + event);
        listeners.forEach(c -> c.accept(event));
    }
}
