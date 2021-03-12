package com.bootcamp.linktracker.repository;

import com.bootcamp.linktracker.dto.LinkDTO;

import java.util.Optional;


public interface LinkTrackerRepository {
    LinkDTO saveLinkDTO(LinkDTO linkDTO);
    Optional<LinkDTO> getLinkDTOByLinkId(String linkId);
    void deleteLinkDTOByLinkId(String linkId);
}
