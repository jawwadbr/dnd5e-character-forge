package com.jawbr.dnd5e.characterforge.dto.mapper.race;

import com.jawbr.dnd5e.characterforge.dto.response.race.RaceAbilityBonusesDTO;
import com.jawbr.dnd5e.characterforge.dto.response.race.RacialAbilityScoreBonusDTO;
import com.jawbr.dnd5e.characterforge.model.entity.RaceAbilityScoreBonus;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RaceAbilityBonusesDTOMapper implements Function<RaceAbilityScoreBonus, RaceAbilityBonusesDTO> {

    @Override
    public RaceAbilityBonusesDTO apply(RaceAbilityScoreBonus abilityScore) {
        return new RaceAbilityBonusesDTO(
                new RacialAbilityScoreBonusDTO(
                        abilityScore.getAbilityScore().getIndexName(),
                        abilityScore.getAbilityScore().getShortName(),
                        abilityScore.getAbilityScore().getUrl()),
                abilityScore.getBonus()
        );
    }
}
