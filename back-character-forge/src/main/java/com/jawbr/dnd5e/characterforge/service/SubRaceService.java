package com.jawbr.dnd5e.characterforge.service;

import com.jawbr.dnd5e.characterforge.dto.mapper.subRace.SubRaceDTOMapper;
import com.jawbr.dnd5e.characterforge.dto.mapper.subRace.SubRaceDTOResponseMapper;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.dto.response.subRace.SubRaceDTO;
import com.jawbr.dnd5e.characterforge.exception.RaceNotFoundException;
import com.jawbr.dnd5e.characterforge.repository.SubRaceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Service
public class SubRaceService {

    private final SubRaceRepository subRaceRepository;
    private final SubRaceDTOMapper subRaceDTOMapper;
    private final SubRaceDTOResponseMapper subRaceDTOResponseMapper;

    private static final String URL = "/api/subraces/";

    public SubRaceService(SubRaceRepository subRaceRepository, SubRaceDTOMapper subRaceDTOMapper, SubRaceDTOResponseMapper subRaceDTOResponseMapper) {
        this.subRaceRepository = subRaceRepository;
        this.subRaceDTOMapper = subRaceDTOMapper;
        this.subRaceDTOResponseMapper = subRaceDTOResponseMapper;
    }

    /**
     * Method to find all sub-races inside the database
     *
     * @return a List containing all sub-races mapped into SubRaceDTO
     * @throws RaceNotFoundException when no sub-races are found inside the database
     * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
     */
    public FindAllDTOResponse findAllSubRaces() {
        return Optional.of(subRaceRepository.findAll())
                .filter(list -> !list.isEmpty())
                .map(subRaceDTOResponseMapper)
                .orElseThrow(() -> new RaceNotFoundException("No sub races found."));
    }

    /**
     * Method to Subrace inside the database using Index Name
     *
     * @return a Subrace mapped into SubRaceDTO
     * @throws RaceNotFoundException when no Subrace is found inside the database
     * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
     */
    public SubRaceDTO findSubRaceByIndexName(String indexName) {
        return Optional.of(subRaceRepository.findByIndexName(indexName))
                .map(subRaceDTOMapper)
                .orElseThrow(() -> new RaceNotFoundException(
                        String.format("Subrace with index name '%s' not found.", indexName)));
    }
}
