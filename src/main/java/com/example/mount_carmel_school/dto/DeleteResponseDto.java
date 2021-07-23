package com.example.mount_carmel_school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeleteResponseDto {

    private String message = "DELETED SUCCESSFULLY";
    private Object object;


    public DeleteResponseDto(String message)
    {
        this.message=message;
    }
    public DeleteResponseDto(Object object)
    {
        this.object=object;
    }

}
