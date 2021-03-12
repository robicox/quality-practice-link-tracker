package com.bootcamp.linktracker.controller;

import com.bootcamp.linktracker.dto.ErrorDTO;
import com.bootcamp.linktracker.exception.InvalidLink;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @ExceptionHandler(InvalidLink.class)
    public ResponseEntity<?> handleInvalidLink(InvalidLink invalidLink) {
        return new ResponseEntity<>(invalidLink.getErrorDTO(), invalidLink.getErrorDTO().getStatus());
    }
}
