package com.bootcamp.linktracker.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class MetricsResponseDTO {
    private String linkId;
    private Integer timesRedirected;
}
