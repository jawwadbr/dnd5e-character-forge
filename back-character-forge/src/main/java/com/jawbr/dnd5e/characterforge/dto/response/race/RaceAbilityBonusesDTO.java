package com.jawbr.dnd5e.characterforge.dto.response.race;

import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import lombok.Builder;

@Builder
public record RaceAbilityBonusesDTO(
        EntityReferenceDTO ability_score,
        int bonus
) {
}
