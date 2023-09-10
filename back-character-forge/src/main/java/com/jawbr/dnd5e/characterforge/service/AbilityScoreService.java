package com.jawbr.dnd5e.characterforge.service;

import com.github.slugify.Slugify;
import com.jawbr.dnd5e.characterforge.dto.mapper.abilityScore.AbilityScoreDTOMapper;
import com.jawbr.dnd5e.characterforge.dto.response.abilityScore.AbilityScoreDTO;
import com.jawbr.dnd5e.characterforge.exception.AbilityScoreNotFoundException;
import com.jawbr.dnd5e.characterforge.repository.AbilityScoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Service
public class AbilityScoreService {

    private final AbilityScoreRepository abilityScoreRepository;
    private final AbilityScoreDTOMapper abilityScoreDTOMapper;
    private final Slugify slugify;

    private static final String URL = "/api/ability-scores/";

    public AbilityScoreService(AbilityScoreRepository abilityScoreRepository,
                               AbilityScoreDTOMapper abilityScoreDTOMapper)
    {
        this.abilityScoreRepository = abilityScoreRepository;
        this.abilityScoreDTOMapper = abilityScoreDTOMapper;
        this.slugify = Slugify.builder().build();
    }

    /**
     * Method to find all Ability Scores inside the database
     *
     * @return a List containing all Ability Scores mapped into AbilityScoreDTO
     * @throws AbilityScoreNotFoundException when no ability score is found inside the database
     * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
     */
    public List<AbilityScoreDTO> findAllAbilityScores() {
        return Optional.of(abilityScoreRepository.findAll())
                .filter(list -> !list.isEmpty())
                .map(list -> list.stream().map(abilityScoreDTOMapper).toList())
                .orElseThrow(() -> new AbilityScoreNotFoundException("No ability scores found."));
    }
}
