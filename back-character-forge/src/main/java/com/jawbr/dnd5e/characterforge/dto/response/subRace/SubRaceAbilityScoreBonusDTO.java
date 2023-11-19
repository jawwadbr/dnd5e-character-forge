package com.jawbr.dnd5e.characterforge.dto.response.subRace;

import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import lombok.Builder;

@Builder
public record SubRaceAbilityScoreBonusDTO(
        EntityReferenceDTO ability_score,
        int bonus
) {
}
