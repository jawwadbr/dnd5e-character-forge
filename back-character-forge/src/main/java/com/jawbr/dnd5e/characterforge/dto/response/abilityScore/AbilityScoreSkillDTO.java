package com.jawbr.dnd5e.characterforge.dto.response.abilityScore;

import lombok.Builder;

@Builder
public record AbilityScoreSkillDTO(
        String index,
        String name,
        String url
) {
}
