//package com.example.mount_carmel_school.service.notification;
//
//import com.example.mount_carmel_school.dto.contact_us_message_dto.ContactUsMessageDtoGet;
//import com.example.mount_carmel_school.dto.parent_dto.ParentDtoGet;
//import com.example.mount_carmel_school.dto.parent_message_dto.ParentMessageDtoGet;
//import com.example.mount_carmel_school.dto.school_news_dto.SchoolNewsDtoGet;
//import com.example.mount_carmel_school.service.notification.processor.NewContactUsMessageNotificationProcessor;
//import com.example.mount_carmel_school.service.notification.processor.NewParentMessageNotificationProcessor;
//import com.example.mount_carmel_school.service.notification.processor.NewSchoolNewsPostNotificationProcessor;
//import com.example.mount_carmel_school.service.notification.processor.ParentRegistrationNotificationProcessor;
//import lombok.NoArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
//import reactor.core.publisher.Flux;
//
//import java.io.IOException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
//
//@NoArgsConstructor
//@Service
//public class NotificationService {
//
//
//    @Autowired
//    ParentRegistrationNotificationProcessor parentProcessor;
//
//    @Autowired
//    NewContactUsMessageNotificationProcessor contactMessageProcessor;
//
//
//    @Autowired
//    NewSchoolNewsPostNotificationProcessor schoolNewsProcessor;
//
//
//    @Autowired
//    NewParentMessageNotificationProcessor parentMessageProcessor;
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);
//    private final ExecutorService executor = Executors.newSingleThreadExecutor();
//
//    public void init() {
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            executor.shutdown();
//            try {
//                executor.awaitTermination(1, TimeUnit.SECONDS);
//            } catch (InterruptedException e) {
//                LOGGER.error(e.toString());
//            }
//        }));
//    }
//
//
//    public Flux<ContactUsMessageDtoGet>  newContactMessages(){
//        return Flux.create(sink -> {
//            contactMessageProcessor.register(sink::next);
//        });
//    }
//
//    public Flux<ParentMessageDtoGet> newParentMessages(){
//        return Flux.create(sink -> {
//            parentMessageProcessor.register(sink::next);
//        });
//    }
//
//
//    public Flux<ParentDtoGet> newParentRegistrations() {
//        return Flux.create(sink -> {
//            parentProcessor.register(sink::next);
//        });
//    }
//
//    public Flux<SchoolNewsDtoGet> newSchoolNewsPost(){
//        return Flux.create(sink -> {
//            schoolNewsProcessor.register(sink::next);
//        });
//    }
//
////
////    private  SseEmitter send(Object data)
////    {
////        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
////
////        sseEmitter.onCompletion(() -> LOGGER.info("SseEmitter is completed"));
////
////        sseEmitter.onTimeout(() -> LOGGER.info("SseEmitter is timed out"));
////
////        sseEmitter.onError((ex) -> LOGGER.info("SseEmitter got error:", ex));
////        executor.execute(() -> {
////        try {
////            sseEmitter.send(data);
////            sleep(1, sseEmitter);
////        } catch (IOException e) {
////            e.printStackTrace();
////            sseEmitter.completeWithError(e);
////        }
////        sseEmitter.complete();
////        });
////        return sseEmitter;
////    }
////
////
////    private void sleep(int seconds, SseEmitter sseEmitter) {
////        try {
////            Thread.sleep(seconds * 1000);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////            sseEmitter.completeWithError(e);
////        }
////    }
//}
