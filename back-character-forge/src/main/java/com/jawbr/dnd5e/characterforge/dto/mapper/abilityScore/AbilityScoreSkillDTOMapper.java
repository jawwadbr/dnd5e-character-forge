package com.jawbr.dnd5e.characterforge.dto.mapper.abilityScore;

import com.jawbr.dnd5e.characterforge.dto.response.abilityScore.AbilityScoreSkillDTO;
import com.jawbr.dnd5e.characterforge.model.entity.Skill;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Method to map {@link Skill} entity to {@link AbilityScoreSkillDTO}
 *
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Service
public class AbilityScoreSkillDTOMapper implements Function<Skill, AbilityScoreSkillDTO> {

    @Override
    public AbilityScoreSkillDTO apply(Skill skill) {
        return new AbilityScoreSkillDTO(
                skill.getIndexName(),
                skill.getName(),
                skill.getUrl()
        );
    }

}
