package com.bootcamp.linktracker.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ErrorDTO {
    private String message;
    private String description;
    private HttpStatus status;
}
