package com.jawbr.dnd5e.characterforge.dto.mapper.race;

import com.jawbr.dnd5e.characterforge.dto.response.race.RaceAbilityBonusesDTO;
import com.jawbr.dnd5e.characterforge.dto.response.race.RacialAbilityScoreBonusDTO;
import com.jawbr.dnd5e.characterforge.model.entity.AbilityScore;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RaceAbilityBonusesDTOMapper implements Function<AbilityScore, RaceAbilityBonusesDTO> {

    @Override
    public RaceAbilityBonusesDTO apply(AbilityScore abilityScore) {
        return new RaceAbilityBonusesDTO(
                new RacialAbilityScoreBonusDTO(
                        abilityScore.getIndexName(),
                        abilityScore.getShortName(),
                        abilityScore.getUrl()),
                1 // TODO - Need to associate a bonus for the ability score
        );
    }
}
