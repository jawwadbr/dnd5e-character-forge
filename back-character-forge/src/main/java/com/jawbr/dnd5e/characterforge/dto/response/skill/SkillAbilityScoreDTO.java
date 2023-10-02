package com.jawbr.dnd5e.characterforge.dto.response.skill;

import lombok.Builder;

@Builder
public record SkillAbilityScoreDTO(
        String index,
        String name,
        String url
) {
}
