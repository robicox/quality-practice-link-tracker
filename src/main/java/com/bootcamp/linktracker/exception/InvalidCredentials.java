package com.bootcamp.linktracker.exception;

import com.bootcamp.linktracker.dto.ErrorDTO;
import org.springframework.http.HttpStatus;

public class InvalidCredentials extends InvalidLink{
    public InvalidCredentials(String message, String description, HttpStatus status) {
        super(ErrorDTO.builder()
                .description(description)
                .message(message)
                .status(status).build()
        );
    }
}
