package com.bootcamp.linktracker.service;

import com.bootcamp.linktracker.dto.LinkDTO;
import com.bootcamp.linktracker.dto.LinkRequestDTO;
import com.bootcamp.linktracker.dto.MetricsResponseDTO;
import com.bootcamp.linktracker.exception.InvalidCredentials;
import com.bootcamp.linktracker.exception.InvalidLinkId;
import com.bootcamp.linktracker.exception.InvalidUrlException;
import com.bootcamp.linktracker.repository.LinkTrackerRepository;
import com.bootcamp.linktracker.util.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LinkTrackerServiceImpl implements LinkTrackerService {
    private static final String URL_PATTERN_REGEX = "[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)";
    private static final int MASKED_URL_LENGTH = 8;

    @Autowired
    private LinkTrackerRepository linkTrackerRepository;

    @Override
    public LinkDTO createMaskedUrl(LinkRequestDTO linkRequestDTO) throws InvalidUrlException {
        if (isValidUrl(linkRequestDTO.getUrl()) || linkRequestDTO.getPassword().isEmpty()) {
            LinkDTO linkDTO = new LinkDTO();
            String maskedUrl = new RandomString(MASKED_URL_LENGTH).nextString();
            linkDTO.setLinkId(maskedUrl);
            linkDTO.setUrl(linkRequestDTO.getUrl());
            linkDTO.setVisitCounter(0);
            linkDTO.setPassword(linkRequestDTO.getPassword());

            linkTrackerRepository.saveLinkDTO(linkDTO);
            return linkDTO;
        } else {
            throw new InvalidUrlException("Invalid url ", linkRequestDTO.getUrl() + " url is invalid", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public LinkDTO getLinkDto(String linkId, String password) throws InvalidLinkId, InvalidCredentials {
        LinkDTO linkDTO = linkTrackerRepository.getLinkDTOByLinkId(linkId)
                .orElseThrow(() -> new InvalidLinkId("LinkId no existe", "Link with id " + linkId + " does not exists", HttpStatus.NOT_FOUND));

        if (linkDTO.getPassword().equals(password)) {
            linkDTO.incrementVisitCounter();
            return linkDTO;
        } else {
            throw new InvalidCredentials("Invalid credentials", "Invalid credentials when accesing the link for redirecting", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public MetricsResponseDTO getMetrics(String linkId) throws InvalidLinkId {
        LinkDTO linkDTO = linkTrackerRepository.getLinkDTOByLinkId(linkId)
                .orElseThrow(() -> new InvalidLinkId("LinkId no existe",
                        "Link with id " + linkId + " does not exists",
                        HttpStatus.NOT_FOUND));

        return MetricsResponseDTO.builder()
                .linkId(linkDTO.getLinkId())
                .timesRedirected(linkDTO.getVisitCounter())
                .build();
    }


    @Override
    public void invalidate(String linkId) {
        linkTrackerRepository.deleteLinkDTOByLinkId(linkId);
    }

    private boolean isValidUrl(String url) {
        return url.matches(URL_PATTERN_REGEX);
    }

}
