package com.jawbr.dnd5e.characterforge.dto.response.proficiency;

import lombok.Builder;

@Builder
public record ProficiencyRaceDTO(
        String index,
        String name,
        String url
) {
}
