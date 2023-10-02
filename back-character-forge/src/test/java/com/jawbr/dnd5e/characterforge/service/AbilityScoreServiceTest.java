package com.jawbr.dnd5e.characterforge.service;

import com.jawbr.dnd5e.characterforge.dto.mapper.abilityScore.AbilityScoreDTOMapper;
import com.jawbr.dnd5e.characterforge.dto.response.abilityScore.AbilityScoreDTO;
import com.jawbr.dnd5e.characterforge.dto.response.abilityScore.AbilityScoreSkillDTO;
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

    private AbilityScore abilityScore;
    private AbilityScoreDTO abilityScoreDTO;
    private Skill skill;

    @BeforeEach
    void init() {
        skill = Skill.builder()
                .id(1)
                .indexName("indexS")
                .name("nameS")
                .skillDesc("descS")
                .url("urlS")
                .build();

        List<Skill> skillList = new ArrayList<>();
        skillList.add(skill);

        abilityScore = AbilityScore.builder()
                .id(1)
                .indexName("indexA")
                .shortName("shortNameA")
                .fullName("fullNameA")
                .desc("descA")
                .url("urlA")
                .skills(skillList)
                .build();

        abilityScoreDTO = AbilityScoreDTO.builder()
                .index(abilityScore.getIndexName())
                .short_name(abilityScore.getShortName())
                .full_name(abilityScore.getFullName())
                .desc(abilityScore.getDesc())
                .skills(new ArrayList<>(Collections.singleton(AbilityScoreSkillDTO.builder()
                        .index(skill.getIndexName())
                        .name(skill.getName())
                        .url(skill.getUrl())
                        .build())))
                .url(abilityScore.getUrl())
                .build();

    }

    @Test
    void findAllAbilityScores() {
        List<AbilityScore> abilityScoreList = new ArrayList<>();
        abilityScoreList.add(abilityScore);

        List<AbilityScoreDTO> expected = new ArrayList<>(Collections.singleton(abilityScoreDTO));

        when(abilityScoreRepository.findAll()).thenReturn(abilityScoreList);
        when(abilityScoreDTOMapper.apply(abilityScore)).thenReturn(abilityScoreDTO);

        List<AbilityScoreDTO> result = abilityScoreService.findAllAbilityScores();

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
}