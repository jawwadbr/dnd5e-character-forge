package com.jawbr.dnd5e.characterforge.dto.mapper.abilityScore;

import com.jawbr.dnd5e.characterforge.dto.response.abilityScore.AbilityScoreDTO;
import com.jawbr.dnd5e.characterforge.model.entity.AbilityScore;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Method to map {@link AbilityScore} entity to {@link AbilityScoreDTO}
 *
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Service
public class AbilityScoreDTOMapper implements Function<AbilityScore, AbilityScoreDTO> {

    private final AbilityScoreSkillDTOMapper abilityScoreSkillDTOMapper;

    public AbilityScoreDTOMapper(AbilityScoreSkillDTOMapper abilityScoreSkillDTOMapper) {
        this.abilityScoreSkillDTOMapper = abilityScoreSkillDTOMapper;
    }

    @Override
    public AbilityScoreDTO apply(AbilityScore abilityScore) {
        return new AbilityScoreDTO(
                abilityScore.getIndexName(),
                abilityScore.getShortName(),
                abilityScore.getFullName(),
                abilityScore.getDesc(),
                abilityScore.getSkills()
                        .stream()
                        .map(abilityScoreSkillDTOMapper)
                        .collect(Collectors.toList()),
                abilityScore.getUrl()
        );
    }

}
