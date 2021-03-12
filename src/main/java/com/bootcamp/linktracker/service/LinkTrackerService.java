package com.bootcamp.linktracker.service;

import com.bootcamp.linktracker.dto.LinkDTO;
import com.bootcamp.linktracker.dto.LinkRequestDTO;
import com.bootcamp.linktracker.dto.MetricsResponseDTO;
import com.bootcamp.linktracker.exception.InvalidCredentials;
import com.bootcamp.linktracker.exception.InvalidLinkId;
import com.bootcamp.linktracker.exception.InvalidUrlException;

public interface LinkTrackerService {
    LinkDTO createMaskedUrl(LinkRequestDTO linkRequestDTO) throws InvalidUrlException;
    LinkDTO getLinkDto(String linkId, String password) throws InvalidLinkId, InvalidCredentials;
    MetricsResponseDTO getMetrics(String linkId) throws InvalidLinkId;
    void invalidate(String linkId);
}
