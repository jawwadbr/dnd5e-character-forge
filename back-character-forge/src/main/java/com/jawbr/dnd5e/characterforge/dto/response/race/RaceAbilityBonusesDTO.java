package com.jawbr.dnd5e.characterforge.dto.response.race;

import lombok.Builder;

@Builder
public record RaceAbilityBonusesDTO(
        RacialAbilityScoreBonusDTO ability_score,
        int bonus
) {
}
