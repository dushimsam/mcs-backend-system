package com.example.mount_carmel_school.dto.contact_us_message_dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.OneToMany;

@Data
public class ContactUsMessageDtoPost {
    private String names;
    private String email;
    private String message;
}
