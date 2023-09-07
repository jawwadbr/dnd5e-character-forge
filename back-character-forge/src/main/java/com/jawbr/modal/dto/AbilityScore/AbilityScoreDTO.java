package com.jawbr.modal.dto.AbilityScore;

import lombok.Builder;

import java.util.List;

@Builder
public record AbilityScoreDTO(
        String indexName,
        String shortName,
        String fullName,
        String desc,
        String url,
        List<SkillForAbilityScoreDTO> skills
) {
}
