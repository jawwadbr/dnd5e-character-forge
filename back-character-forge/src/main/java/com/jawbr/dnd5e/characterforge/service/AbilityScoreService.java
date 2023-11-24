package com.jawbr.dnd5e.characterforge.service;

import com.github.slugify.Slugify;
import com.jawbr.dnd5e.characterforge.dto.mapper.abilityScore.AbilityScoreDTOMapper;
import com.jawbr.dnd5e.characterforge.dto.mapper.abilityScore.AbilityScoreDTOResponseMapper;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.dto.response.abilityScore.AbilityScoreDTO;
import com.jawbr.dnd5e.characterforge.exception.AbilityScoreNotFoundException;
import com.jawbr.dnd5e.characterforge.model.entity.AbilityScore;
import com.jawbr.dnd5e.characterforge.model.entity.Language;
import com.jawbr.dnd5e.characterforge.repository.AbilityScoreRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Service
public class AbilityScoreService {

    private final AbilityScoreRepository abilityScoreRepository;
    private final AbilityScoreDTOMapper abilityScoreDTOMapper;
    private final AbilityScoreDTOResponseMapper abilityScoreDTOResponseMapper;
    private final Slugify slugify;

    private static final String URL = "/api/ability-scores/";

    public AbilityScoreService(AbilityScoreRepository abilityScoreRepository,
                               AbilityScoreDTOMapper abilityScoreDTOMapper, AbilityScoreDTOResponseMapper abilityScoreDTOResponseMapper)
    {
        this.abilityScoreRepository = abilityScoreRepository;
        this.abilityScoreDTOMapper = abilityScoreDTOMapper;
        this.abilityScoreDTOResponseMapper = abilityScoreDTOResponseMapper;
        this.slugify = Slugify.builder().build();
    }

    /**
     * Method to find all Ability Scores inside the database in alphabetical order
     *
     * @return a List containing all Ability Scores mapped into AbilityScoreDTO
     * @throws AbilityScoreNotFoundException when no ability score is found inside the database
     * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
     */
    public FindAllDTOResponse findAllAbilityScores() {
        return Optional.of(abilityScoreRepository.findAll())
                .filter(list -> !list.isEmpty())
                .map(list -> list.stream()
                        .sorted(Comparator.comparing(AbilityScore::getShortName))
                        .collect(Collectors.toList()))
                .map(abilityScoreDTOResponseMapper)
                .orElseThrow(() -> new AbilityScoreNotFoundException("No ability scores found."));
    }

    /**
     * Method to find Ability Scores inside the database using Index Name
     *
     * @return a Ability Scores mapped into AbilityScoreDTO
     * @throws AbilityScoreNotFoundException when no ability score is found inside the database
     * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
     */
    public AbilityScoreDTO findAbilityScoresByIndexName(String indexName) {
        return Optional.ofNullable(abilityScoreRepository.findByIndexName(indexName))
                .map(abilityScoreDTOMapper)
                .orElseThrow(() -> new AbilityScoreNotFoundException(
                        String.format("Ability score with index name '%s' not found.", indexName)));
    }
}
