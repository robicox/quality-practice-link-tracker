package com.bootcamp.linktracker.repository;

import com.bootcamp.linktracker.dto.LinkDTO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class LinkTrackerRepositoryImpl implements LinkTrackerRepository {
    private Map<String, LinkDTO> links;

    public LinkTrackerRepositoryImpl() {
        this.links = new HashMap<>();
    }

    @Override
    public LinkDTO saveLinkDTO(LinkDTO linkDTO) {
        return links.put(linkDTO.getLinkId(), linkDTO);
    }

    @Override
    public Optional<LinkDTO> getLinkDTOByLinkId(String linkId) {
        return Optional.ofNullable(links.get(linkId));
    }

    @Override
    public void deleteLinkDTOByLinkId(String linkId) {
        links.remove(linkId);
    }
}
