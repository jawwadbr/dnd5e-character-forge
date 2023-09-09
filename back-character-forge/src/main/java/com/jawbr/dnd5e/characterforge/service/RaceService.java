package com.jawbr.dnd5e.characterforge.service;

import com.github.slugify.Slugify;
import com.jawbr.dnd5e.characterforge.dto.mapper.race.RaceDTOMapper;
import com.jawbr.dnd5e.characterforge.dto.response.race.RaceDTO;
import com.jawbr.dnd5e.characterforge.exception.RaceNotFoundException;
import com.jawbr.dnd5e.characterforge.repository.RaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RaceService {

    private final RaceRepository raceRepository;
    private final RaceDTOMapper raceDTOMapper;
    private final Slugify slugify;

    private static final String URL = "/api/races/";

    public RaceService(RaceRepository raceRepository, RaceDTOMapper raceDTOMapper) {
        this.raceRepository = raceRepository;
        this.raceDTOMapper = raceDTOMapper;
        this.slugify = Slugify.builder().build();
    }

    public List<RaceDTO> findAllRaces() {
        return Optional.of(raceRepository.findAll())
                .filter(list -> !list.isEmpty())
                .map(list -> list.stream().map(raceDTOMapper).toList())
                .orElseThrow(() -> new RaceNotFoundException("No races found."));
    }
}
