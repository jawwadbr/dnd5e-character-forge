package com.jawbr.modal.dto.AbilityScore;

import lombok.Builder;

@Builder
public record SkillForAbilityScoreDTO(
        String indexName,
        String name,
        String desc,
        String url
) {
}
