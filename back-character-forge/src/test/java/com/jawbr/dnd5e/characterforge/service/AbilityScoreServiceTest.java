package com.jawbr.dnd5e.characterforge.service;

import com.jawbr.dnd5e.characterforge.dto.mapper.abilityScore.AbilityScoreDTOMapper;
import com.jawbr.dnd5e.characterforge.dto.mapper.abilityScore.AbilityScoreDTOResponseMapper;
import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.dto.response.abilityScore.AbilityScoreDTO;
import com.jawbr.dnd5e.characterforge.exception.AbilityScoreNotFoundException;
import com.jawbr.dnd5e.characterforge.model.entity.AbilityScore;
import com.jawbr.dnd5e.characterforge.model.entity.Skill;
import com.jawbr.dnd5e.characterforge.repository.AbilityScoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class AbilityScoreServiceTest {

    @InjectMocks
    private AbilityScoreService abilityScoreService;

    @Mock
    private AbilityScoreRepository abilityScoreRepository;

    @Mock
    private AbilityScoreDTOMapper abilityScoreDTOMapper;

    @Mock
    private AbilityScoreDTOResponseMapper abilityScoreDTOResponseMapper;

    private AbilityScore abilityScore;
    private FindAllDTOResponse abilityScoreResponse;
    private AbilityScoreDTO abilityScoreDTO;

    @BeforeEach
    void init() {

        Skill skill = Skill.builder()
                .indexName("acrobatics")
                .name("Acrobatics")
                .url("/api/skills/acrobatics")
                .skillDesc("desc")
                .build();

        List<Skill> skillListEntity = new ArrayList<>();
        skillListEntity.add(skill);

        abilityScore = AbilityScore.builder()
                .indexName("dex")
                .fullName("Dexterity")
                .shortName("DEX")
                .desc("desc")
                .url("/api/ability-scores/dex")
                .skills(skillListEntity)
                .build();

        EntityReferenceDTO abilityScoreForFindAll = EntityReferenceDTO.builder()
                .name("DEX")
                .index("dex")
                .url("/api/ability-scores/dex")
                .build();

        List<EntityReferenceDTO> findAllResponseAbilityScores = new ArrayList<>();
        findAllResponseAbilityScores.add(abilityScoreForFindAll);

        abilityScoreResponse = FindAllDTOResponse.builder()
                .count(findAllResponseAbilityScores.size())
                .results(findAllResponseAbilityScores)
                .build();

        abilityScoreDTO = AbilityScoreDTO.builder()
                .index(abilityScore.getIndexName())
                .short_name(abilityScore.getShortName())
                .full_name(abilityScore.getFullName())
                .desc(abilityScore.getDesc())
                .url(abilityScore.getUrl())
                .skills(Collections.singletonList(EntityReferenceDTO.builder()
                        .index(skill.getIndexName())
                        .name(skill.getName())
                        .url(skill.getUrl())
                        .build()))
                .build();
    }

    @Test
    void findAllAbilityScores() {
        List<AbilityScore> abilityScoreList = new ArrayList<>();
        abilityScoreList.add(abilityScore);

        FindAllDTOResponse expected = abilityScoreResponse;

        when(abilityScoreRepository.findAll()).thenReturn(abilityScoreList);
        when(abilityScoreDTOResponseMapper.apply(abilityScoreList)).thenReturn(abilityScoreResponse);

        FindAllDTOResponse result = abilityScoreService.findAllAbilityScores();

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void findAbilityScoreByIndexName() {
        AbilityScoreDTO expected = abilityScoreDTO;

        when(abilityScoreRepository.findByIndexName(abilityScore.getIndexName())).thenReturn(abilityScore);
        when(abilityScoreDTOMapper.apply(abilityScore)).thenReturn(abilityScoreDTO);

        AbilityScoreDTO result = abilityScoreService.findAbilityScoresByIndexName(abilityScore.getIndexName());

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void cannotFindAllAbilityScores() {
        List<AbilityScore> abilityScoreList = new ArrayList<>();
        when(abilityScoreRepository.findAll()).thenReturn(abilityScoreList);

        assertThrows(AbilityScoreNotFoundException.class,
                () -> abilityScoreService.findAllAbilityScores(),
                "No ability scores found.");
    }

    @Test
    void cannotFindAbilityScoreByIndexName() {
        when(abilityScoreRepository.findByIndexName(abilityScore.getIndexName())).thenReturn(null);

        assertThrows(AbilityScoreNotFoundException.class,
                () -> abilityScoreService.findAbilityScoresByIndexName(abilityScore.getIndexName()),
                String.format("Ability score with index name '%s' not found.", abilityScore.getIndexName()));
    }
}
