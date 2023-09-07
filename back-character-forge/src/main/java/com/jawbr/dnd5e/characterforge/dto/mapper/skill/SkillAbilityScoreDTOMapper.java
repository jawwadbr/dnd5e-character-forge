package com.jawbr.dnd5e.characterforge.dto.mapper.skill;

import com.jawbr.dnd5e.characterforge.dto.response.skill.SkillAbilityScoreDTO;
import com.jawbr.dnd5e.characterforge.model.entity.AbilityScore;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SkillAbilityScoreDTOMapper implements Function<AbilityScore, SkillAbilityScoreDTO> {

    @Override
    public SkillAbilityScoreDTO apply(AbilityScore abilityScore) {
        return new SkillAbilityScoreDTO(
                abilityScore.getIndexName(),
                abilityScore.getFullName(),
                abilityScore.getUrl()
        );
    }

}
