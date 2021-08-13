//package com.example.mount_carmel_school.service.notification.processor;
//
//import com.example.mount_carmel_school.dto.contact_us_message_dto.ContactUsMessageDtoGet;
//import com.example.mount_carmel_school.dto.school_news_dto.SchoolNewsDtoGet;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.concurrent.CopyOnWriteArrayList;
//import java.util.function.Consumer;
//
//@Service
//public class NewSchoolNewsPostNotificationProcessor {
//    private static final Logger LOGGER = LoggerFactory.getLogger(NewSchoolNewsPostNotificationProcessor.class);
//
//    private List<Consumer<SchoolNewsDtoGet>> listeners = new CopyOnWriteArrayList< >();
//
//    public void register(Consumer < SchoolNewsDtoGet > listener) {
//        listeners.add(listener);
//        LOGGER.info("Added a listener, for a total of {} listener{}", listeners.size(), listeners.size() > 1 ? "s" : "");
//    }
//
//    // TODO FBE implement unregister
//
//    public void process(SchoolNewsDtoGet event) {
////        System.out.println("Processing: " + event);
//        listeners.forEach(c -> c.accept(event));
//    }
//}
