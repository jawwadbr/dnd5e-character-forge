package com.jawbr.dnd5e.characterforge.service;

import com.github.slugify.Slugify;
import com.jawbr.dnd5e.characterforge.dto.mapper.proficiency.ProficiencyDTOMapper;
import com.jawbr.dnd5e.characterforge.dto.mapper.proficiency.ProficiencyDTOResponseMapper;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.dto.response.proficiency.ProficiencyDTO;
import com.jawbr.dnd5e.characterforge.exception.ProficiencyNotFoundException;
import com.jawbr.dnd5e.characterforge.repository.ProficiencyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Service
public class ProficiencyService {

    private final ProficiencyRepository proficiencyRepository;
    private final ProficiencyDTOMapper proficiencyDTOMapper;
    private final ProficiencyDTOResponseMapper proficiencyDTOResponseMapper;
    private final Slugify slugify;

    private static final String URL = "/api/proficiencies/";

    public ProficiencyService(ProficiencyRepository proficiencyRepository, ProficiencyDTOMapper proficiencyDTOMapper, ProficiencyDTOResponseMapper proficiencyDTOResponseMapper) {
        this.proficiencyRepository = proficiencyRepository;
        this.proficiencyDTOMapper = proficiencyDTOMapper;
        this.proficiencyDTOResponseMapper = proficiencyDTOResponseMapper;
        this.slugify = Slugify.builder().build();
    }

    /**
     * Method to find all proficiencies inside the database
     *
     * @return a List containing all proficiencies mapped into ProficiencyDTO
     * @throws ProficiencyNotFoundException when no proficiencies are found inside the database
     * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
     */
    public FindAllDTOResponse findAllProficiencies() {
        return Optional.of(proficiencyRepository.findAll())
                .filter(list -> !list.isEmpty())
                .map(proficiencyDTOResponseMapper)
                .orElseThrow(() -> new ProficiencyNotFoundException("No proficiencies found."));
    }

    /**
     * Method to find proficiency inside the database using Index Name
     *
     * @return a proficiency mapped into ProficiencyDTO
     * @throws ProficiencyNotFoundException when no proficiencies are found inside the database
     * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
     */
    public ProficiencyDTO findByIndexName(String indexName) {
        return Optional.of(proficiencyRepository.findByIndexName(indexName))
                .map(proficiencyDTOMapper)
                .orElseThrow(() -> new ProficiencyNotFoundException(
                        String.format("Proficiency with index name '%s' not found.", indexName)));
    }
}
