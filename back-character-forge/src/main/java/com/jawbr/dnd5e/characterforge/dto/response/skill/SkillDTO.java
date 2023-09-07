package com.jawbr.dnd5e.characterforge.dto.response.skill;

import lombok.Builder;

import java.util.List;

@Builder
public record SkillDTO(
        String index,
        String name,
        String desc,
        SkillAbilityScoreDTO ability_score,
        String url
) {
}
