package com.bootcamp.linktracker.controller;

import com.bootcamp.linktracker.dto.LinkDTO;
import com.bootcamp.linktracker.dto.LinkRequestDTO;
import com.bootcamp.linktracker.dto.MetricsResponseDTO;
import com.bootcamp.linktracker.exception.InvalidCredentials;
import com.bootcamp.linktracker.exception.InvalidLinkId;
import com.bootcamp.linktracker.exception.InvalidUrlException;
import com.bootcamp.linktracker.service.LinkTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;

@RestController
@RequestMapping("/link")
public class LinkTrackerController extends BaseController {
    @Autowired
    private LinkTrackerService linkTrackerService;

    @PostMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public LinkDTO maskUrl(@RequestBody final LinkRequestDTO linkDTORequest) throws InvalidUrlException {
        return linkTrackerService.createMaskedUrl(linkDTORequest);
    }

    @GetMapping(value = "/{linkId}")
    public ResponseEntity goToUrl(@PathVariable String linkId, @RequestParam(name = "password", defaultValue = "") final String password, RedirectAttributes redirectAttributes) throws InvalidCredentials, InvalidLinkId {  // Inyecta redirectAttrs
        LinkDTO linkDTO = linkTrackerService.getLinkDto(linkId, password);
        String url = linkDTO.getUrl();

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();

    }

    @GetMapping(value = "/metrics/{linkId}")
    public MetricsResponseDTO getMetrics(@PathVariable final String linkId) throws InvalidLinkId {
        return linkTrackerService.getMetrics(linkId);
    }

    @PostMapping(value = "/invalidate/{linkId}")
    public ResponseEntity invalidateLink(@PathVariable final String linkId) {
        linkTrackerService.invalidate(linkId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
