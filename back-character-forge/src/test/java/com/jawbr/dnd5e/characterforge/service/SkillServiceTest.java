package com.jawbr.dnd5e.characterforge.service;

import com.jawbr.dnd5e.characterforge.dto.mapper.skill.SkillDTOMapper;
import com.jawbr.dnd5e.characterforge.dto.response.skill.SkillAbilityScoreDTO;
import com.jawbr.dnd5e.characterforge.dto.response.skill.SkillDTO;
import com.jawbr.dnd5e.characterforge.exception.SkillNotFoundException;
import com.jawbr.dnd5e.characterforge.model.entity.AbilityScore;
import com.jawbr.dnd5e.characterforge.model.entity.Skill;
import com.jawbr.dnd5e.characterforge.repository.SkillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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

@Disabled
@SpringBootTest
class SkillServiceTest {

    @InjectMocks
    private SkillService skillService;

    @Mock
    private SkillRepository skillRepository;

    @Mock
    private SkillDTOMapper skillDTOMapper;

    private Skill skill;
    private SkillDTO skillDTO;

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

        AbilityScore abilityScore = AbilityScore.builder()
                .id(1)
                .indexName("indexA")
                .shortName("shortNameA")
                .fullName("fullNameA")
                .desc("descA")
                .url("urlA")
                .skills(skillList)
                .build();

        skill.setAbilityScore(abilityScore);

        skillDTO = SkillDTO.builder()
                .index(skill.getIndexName())
                .name(skill.getName())
                .desc(skill.getSkillDesc())
                .url(skill.getUrl())
                .ability_score(SkillAbilityScoreDTO.builder()
                        .index(abilityScore.getIndexName())
                        .name(abilityScore.getShortName())
                        .url(abilityScore.getUrl())
                        .build())
                .build();
    }

    @Test
    void findAllSkills() {
        List<Skill> skillList = new ArrayList<>();
        skillList.add(skill);

        List<SkillDTO> expected = new ArrayList<>();
        expected.add(skillDTO);

        when(skillRepository.findAll()).thenReturn(skillList);
        when(skillDTOMapper.apply(skill)).thenReturn(skillDTO);

        //List<SkillDTO> result = skillService.findAllSkills();

        //assertNotNull(result);
        //assertEquals(expected, result);
    }

    @Test
    void cannotFindAllSkills() {
        List<Skill> skillList = new ArrayList<>();
        when(skillRepository.findAll()).thenReturn(skillList);

        assertThrows(SkillNotFoundException.class, () -> skillService.findAllSkills(), "No skills found.");
    }
}