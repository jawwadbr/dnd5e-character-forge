package com.jawbr.dnd5e.characterforge.service;

import com.github.slugify.Slugify;
import com.jawbr.dnd5e.characterforge.dto.mapper.race.RaceDTOMapper;
import com.jawbr.dnd5e.characterforge.dto.mapper.race.RaceDTOResponseMapper;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.dto.response.race.RaceDTO;
import com.jawbr.dnd5e.characterforge.exception.RaceNotFoundException;
import com.jawbr.dnd5e.characterforge.model.entity.Language;
import com.jawbr.dnd5e.characterforge.model.entity.Race;
import com.jawbr.dnd5e.characterforge.repository.RaceRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Service
public class RaceService {

    private final RaceRepository raceRepository;
    private final RaceDTOMapper raceDTOMapper;
    private final RaceDTOResponseMapper raceDTOResponseMapper;
    private final Slugify slugify;

    private static final String URL = "/api/races/";

    public RaceService(RaceRepository raceRepository,
                       RaceDTOMapper raceDTOMapper, RaceDTOResponseMapper raceDTOResponseMapper)
    {
        this.raceRepository = raceRepository;
        this.raceDTOMapper = raceDTOMapper;
        this.raceDTOResponseMapper = raceDTOResponseMapper;
        this.slugify = Slugify.builder().build();
    }

    /**
     * Method to find all Races inside the database in alphabetical order
     *
     * @return a List containing all Races mapped into RaceDTO
     * @throws RaceNotFoundException when no races are found inside the database
     * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
     */
    public FindAllDTOResponse findAllRaces() {
        return Optional.of(raceRepository.findAll())
                .filter(list -> !list.isEmpty())
                .map(list -> list.stream()
                        .sorted(Comparator.comparing(Race::getRaceName))
                        .collect(Collectors.toList()))
                .map(raceDTOResponseMapper)
                .orElseThrow(() -> new RaceNotFoundException("No races found."));
    }

    /**
     * Method to find Race inside the database using Index Name
     *
     * @return a Race mapped into RaceDTO
     * @throws RaceNotFoundException when no race is found inside the database
     * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
     */
    public RaceDTO findRaceByIndexName(String indexName) {
        return Optional.ofNullable(raceRepository.findByIndexName(indexName))
                .map(raceDTOMapper)
                .orElseThrow(() -> new RaceNotFoundException(
                        String.format("Race with index name '%s' not found.", indexName)));
    }
}
