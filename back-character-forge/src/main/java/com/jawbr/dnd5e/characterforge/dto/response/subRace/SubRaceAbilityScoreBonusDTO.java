package com.jawbr.dnd5e.characterforge.dto.response.subRace;

import lombok.Builder;

@Builder
public record SubRaceAbilityScoreBonusDTO(
        SubRaceAbilityScoreDTO ability_score,
        int bonus
) {
}
