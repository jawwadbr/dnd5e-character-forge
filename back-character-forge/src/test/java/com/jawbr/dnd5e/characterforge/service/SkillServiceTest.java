package com.jawbr.dnd5e.characterforge.service;

import com.jawbr.dnd5e.characterforge.dto.mapper.skill.SkillDTOMapper;
import com.jawbr.dnd5e.characterforge.dto.mapper.skill.SkillDTOResponseMapper;
import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.dto.response.skill.SkillDTO;
import com.jawbr.dnd5e.characterforge.exception.SkillNotFoundException;
import com.jawbr.dnd5e.characterforge.model.entity.AbilityScore;
import com.jawbr.dnd5e.characterforge.model.entity.Skill;
import com.jawbr.dnd5e.characterforge.repository.SkillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class SkillServiceTest {

    @InjectMocks
    private SkillService skillService;

    @Mock
    private SkillRepository skillRepository;

    @Mock
    private SkillDTOMapper skillDTOMapper;

    @Mock
    private SkillDTOResponseMapper skillDTOResponseMapper;

    private Skill skill;
    private SkillDTO skillDTO;
    private FindAllDTOResponse skillResponse;

    @BeforeEach
    void init() {
        skill = Skill.builder()
                .id(1)
                .indexName("acrobatics")
                .name("Acrobatics")
                .skillDesc("descS")
                .url("/api/skills/acrobatics")
                .build();

        List<Skill> skillList = new ArrayList<>();
        skillList.add(skill);

        AbilityScore abilityScore = AbilityScore.builder()
                .id(1)
                .indexName("dex")
                .shortName("DEX")
                .fullName("Dexterity")
                .desc("desc")
                .url("/api/ability-scores/dex")
                .skills(skillList)
                .build();

        skill.setAbilityScore(abilityScore);

        skillDTO = SkillDTO.builder()
                .index(skill.getIndexName())
                .name(skill.getName())
                .desc(skill.getSkillDesc())
                .url(skill.getUrl())
                .ability_score(EntityReferenceDTO.builder()
                        .index(abilityScore.getIndexName())
                        .name(abilityScore.getShortName())
                        .url(abilityScore.getUrl())
                        .build())
                .build();

        skillResponse = FindAllDTOResponse.builder()
                .results(List.of(EntityReferenceDTO.builder()
                        .name(skill.getName())
                        .index(skill.getIndexName())
                        .url(skill.getUrl())
                        .build()))
                .count(1)
                .build();
    }

    @Test
    void findAllSkills() {
        List<Skill> skillList = new ArrayList<>();
        skillList.add(skill);

        FindAllDTOResponse expected = skillResponse;

        when(skillRepository.findAll()).thenReturn(skillList);
        when(skillDTOResponseMapper.apply(skillList)).thenReturn(skillResponse);

        FindAllDTOResponse result = skillService.findAllSkills();

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void findSkillByIndexName() {
        SkillDTO expected = skillDTO;

        when(skillRepository.findByIndexName(skill.getIndexName())).thenReturn(skill);
        when(skillDTOMapper.apply(skill)).thenReturn(skillDTO);

        SkillDTO result = skillService.findSkillByIndexName(skill.getIndexName());

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void cannotFindAllSkills() {
        List<Skill> skillList = new ArrayList<>();
        when(skillRepository.findAll()).thenReturn(skillList);

        assertThrows(SkillNotFoundException.class,
                () -> skillService.findAllSkills(),
                "No skills found.");
    }

    @Test
    void cannotFindSkillByIndexName() {
        when(skillRepository.findByIndexName(skill.getIndexName())).thenReturn(null);

        assertThrows(SkillNotFoundException.class,
                () -> skillService.findSkillByIndexName(skill.getIndexName()),
                String.format("Skill with index name '%s' not found.", skill.getIndexName()));
    }
}