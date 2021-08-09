package com.example.mount_carmel_school.service.notification.processor;

import com.example.mount_carmel_school.dto.contact_us_message_dto.ContactUsMessageDtoGet;
import com.example.mount_carmel_school.dto.parent_dto.ParentDtoGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

@Service
public class NewContactUsMessageNotificationProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewContactUsMessageNotificationProcessor.class);

    private List<Consumer<ContactUsMessageDtoGet>> listeners = new CopyOnWriteArrayList< >();

    public void register(Consumer < ContactUsMessageDtoGet > listener) {
        listeners.add(listener);
        LOGGER.info("Added a listener, for a total of {} listener{}", listeners.size(), listeners.size() > 1 ? "s" : "");
    }

    // TODO FBE implement unregister

    public void process(ContactUsMessageDtoGet event) {
//        System.out.println("Processing: " + event);
        listeners.forEach(c -> c.accept(event));
    }
}
