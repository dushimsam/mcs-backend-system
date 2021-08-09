package com.example.mount_carmel_school.controller;

import com.example.mount_carmel_school.dto.contact_us_message_dto.ContactUsMessageDtoGet;
import com.example.mount_carmel_school.dto.parent_dto.ParentDtoGet;
import com.example.mount_carmel_school.dto.parent_message_dto.ParentMessageDtoGet;
import com.example.mount_carmel_school.dto.school_news_dto.SchoolNewsDtoGet;
import com.example.mount_carmel_school.service.notification.NotificationService;
import lombok.AllArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;


@RestController
@RequestMapping(path = "api/v1/notifications")
@AllArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostConstruct
    public void init() {
        notificationService.init();
    }

    @GetMapping(path = "/parent-registrations/new",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ParentDtoGet> newParentRegistration() {
           return notificationService.newParentRegistrations();
    }


    @GetMapping(path = "/contact-messages/new",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ContactUsMessageDtoGet> newContactMessage() {
        return notificationService.newContactMessages();
    }


    @GetMapping(path = "/parent-messages/new",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ParentMessageDtoGet> newParentMessages() {
        return notificationService.newParentMessages();
    }

    @GetMapping(path = "/school-news-post/new",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<SchoolNewsDtoGet> newSchoolNewsPost() {
        return notificationService.newSchoolNewsPost();
    }
}
