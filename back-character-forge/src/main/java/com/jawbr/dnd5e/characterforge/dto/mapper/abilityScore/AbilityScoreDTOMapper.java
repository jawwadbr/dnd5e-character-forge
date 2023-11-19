package com.jawbr.dnd5e.characterforge.dto.mapper.abilityScore;

import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.abilityScore.AbilityScoreDTO;
import com.jawbr.dnd5e.characterforge.model.entity.AbilityScore;
import com.jawbr.dnd5e.characterforge.model.entity.Skill;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Method to map {@link AbilityScore} entity to {@link AbilityScoreDTO}
 *
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Service
public class AbilityScoreDTOMapper implements Function<AbilityScore, AbilityScoreDTO> {

    @Override
    public AbilityScoreDTO apply(AbilityScore abilityScore) {

        List<EntityReferenceDTO> skillList = new ArrayList<>();
        for(Skill skill : abilityScore.getSkills()) {
            skillList.add(EntityReferenceDTO.builder()
                    .name(skill.getName())
                    .index(skill.getIndexName())
                    .url(skill.getUrl())
                    .build());
        }

        return new AbilityScoreDTO(
                abilityScore.getIndexName(),
                abilityScore.getShortName(),
                abilityScore.getFullName(),
                abilityScore.getDesc(),
                skillList,
                abilityScore.getUrl()
        );
    }

}
