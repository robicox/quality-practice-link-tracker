package com.bootcamp.linktracker.exception;

import com.bootcamp.linktracker.dto.ErrorDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class InvalidLink extends Exception{
    private ErrorDTO errorDTO;

    public InvalidLink(ErrorDTO errorDTO) {
        super();
        this.errorDTO = errorDTO;
    }
}
