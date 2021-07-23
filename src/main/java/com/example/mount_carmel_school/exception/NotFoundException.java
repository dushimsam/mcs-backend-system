package com.example.mount_carmel_school.exception;

public class NotFoundException extends ApiRequestException{
    public NotFoundException(String subject) {
        super("Sorry "+subject+" is not found");
    }
}
