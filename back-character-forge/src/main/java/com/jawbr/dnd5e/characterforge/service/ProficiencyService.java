package com.jawbr.dnd5e.characterforge.service;

import com.github.slugify.Slugify;
import com.jawbr.dnd5e.characterforge.dto.mapper.proficiency.ProficiencyDTOMapper;
import com.jawbr.dnd5e.characterforge.dto.response.proficiency.ProficiencyDTO;
import com.jawbr.dnd5e.characterforge.exception.ProficiencyNotFoundException;
import com.jawbr.dnd5e.characterforge.repository.ProficiencyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Service
public class ProficiencyService {

    private final ProficiencyRepository proficiencyRepository;
    private final ProficiencyDTOMapper proficiencyDTOMapper;
    private final Slugify slugify;

    private static final String URL = "/api/proficiencies/";

    public ProficiencyService(ProficiencyRepository proficiencyRepository, ProficiencyDTOMapper proficiencyDTOMapper) {
        this.proficiencyRepository = proficiencyRepository;
        this.proficiencyDTOMapper = proficiencyDTOMapper;
        this.slugify = Slugify.builder().build();
    }

    /**
     * Method to find all proficiencies inside the database
     *
     * @return a List containing all proficiencies mapped into ProficiencyDTO
     * @throws ProficiencyNotFoundException when no languages are found inside the database
     * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
     */
    public List<ProficiencyDTO> findAllProficiencies() {
        return Optional.of(proficiencyRepository.findAll())
                .filter(list -> !list.isEmpty())
                .map(list -> list.stream().map(proficiencyDTOMapper).toList())
                .orElseThrow(() -> new ProficiencyNotFoundException("No proficiencies found."));
    }
}
