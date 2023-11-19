package com.jawbr.dnd5e.characterforge.dto.mapper.race;

import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.race.RaceAbilityBonusesDTO;
import com.jawbr.dnd5e.characterforge.model.entity.RaceAbilityScoreBonus;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Method to map {@link RaceAbilityScoreBonus} entity to {@link RaceAbilityBonusesDTO}
 *
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Service
public class RaceAbilityBonusesDTOMapper implements Function<RaceAbilityScoreBonus, RaceAbilityBonusesDTO> {

    @Override
    public RaceAbilityBonusesDTO apply(RaceAbilityScoreBonus abilityScore) {
        return new RaceAbilityBonusesDTO(
                new EntityReferenceDTO(
                        abilityScore.getAbilityScore().getIndexName(),
                        abilityScore.getAbilityScore().getShortName(),
                        abilityScore.getAbilityScore().getUrl()),
                abilityScore.getBonus()
        );
    }
}
