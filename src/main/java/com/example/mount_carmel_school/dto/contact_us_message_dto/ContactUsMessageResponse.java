package com.example.mount_carmel_school.dto.contact_us_message_dto;

import com.example.mount_carmel_school.dto.school_employee_dto.SchoolEmployeeDtoGet;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ContactUsMessageResponse {
    private List<ContactUsMessageDtoGet> contactUsMessages;
    private Long numberOfItems;
    private int numberOfPages;
}
