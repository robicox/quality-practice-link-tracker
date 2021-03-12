package com.bootcamp.linktracker.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkDTO {
    private String url;
    private String linkId;
    private Integer visitCounter;
    @JsonIgnore
    private String password;

    public void incrementVisitCounter(){
        visitCounter++;
    }
}
