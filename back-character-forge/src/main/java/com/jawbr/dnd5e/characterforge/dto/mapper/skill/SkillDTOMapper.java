package com.jawbr.dnd5e.characterforge.dto.mapper.skill;

import com.jawbr.dnd5e.characterforge.dto.response.skill.SkillDTO;
import com.jawbr.dnd5e.characterforge.model.entity.Skill;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SkillDTOMapper implements Function<Skill, SkillDTO> {

    private final SkillAbilityScoreDTOMapper abilityScoreForSkillDTOMapper;

    public SkillDTOMapper(SkillAbilityScoreDTOMapper abilityScoreForSkillDTOMapper) {
        this.abilityScoreForSkillDTOMapper = abilityScoreForSkillDTOMapper;
    }

    @Override
    public SkillDTO apply(Skill skill) {
        return new SkillDTO(
                skill.getIndexName(),
                skill.getName(),
                skill.getSkillDesc(),
                abilityScoreForSkillDTOMapper.apply(skill.getAbilityScore()),
                skill.getUrl()
        );
    }

}
