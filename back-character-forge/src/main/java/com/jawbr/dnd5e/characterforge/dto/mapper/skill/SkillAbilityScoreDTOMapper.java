package com.jawbr.dnd5e.characterforge.dto.mapper.skill;

import com.jawbr.dnd5e.characterforge.dto.response.skill.SkillAbilityScoreDTO;
import com.jawbr.dnd5e.characterforge.model.entity.AbilityScore;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Method to map {@link AbilityScore} entity to {@link SkillAbilityScoreDTO}
 *
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
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
