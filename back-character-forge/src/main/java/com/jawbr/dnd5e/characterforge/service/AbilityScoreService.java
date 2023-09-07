package com.jawbr.dnd5e.characterforge.service;

import com.github.slugify.Slugify;
import com.jawbr.dnd5e.characterforge.dto.mapper.abilityScore.AbilityScoreDTOMapper;
import com.jawbr.dnd5e.characterforge.dto.response.abilityScore.AbilityScoreDTO;
import com.jawbr.dnd5e.characterforge.repository.AbilityScoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbilityScoreService {

    private final AbilityScoreRepository abilityScoreRepository;
    private final AbilityScoreDTOMapper abilityScoreDTOMapper;
    private final Slugify slugify;

    private static final String URL = "/api/ability-scores/";

    public AbilityScoreService(AbilityScoreRepository abilityScoreRepository, AbilityScoreDTOMapper abilityScoreDTOMapper) {
        this.abilityScoreRepository = abilityScoreRepository;
        this.abilityScoreDTOMapper = abilityScoreDTOMapper;
        this.slugify = Slugify.builder().build();
    }

    public List<AbilityScoreDTO> findAllAbilityScores() {
        return Optional.of(abilityScoreRepository.findAll())
                .filter(list -> !list.isEmpty())
                .map(list -> list.stream().map(abilityScoreDTOMapper).toList())
                .orElseThrow(() -> new RuntimeException("No ability scores found!"));
    }
}
