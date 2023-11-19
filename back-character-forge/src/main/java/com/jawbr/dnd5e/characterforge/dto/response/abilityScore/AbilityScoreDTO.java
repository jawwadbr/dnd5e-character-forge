package com.jawbr.dnd5e.characterforge.dto.response.abilityScore;

import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import lombok.Builder;

import java.util.List;

@Builder
public record AbilityScoreDTO(
        String index,
        String short_name,
        String full_name,
        String desc,
        List<EntityReferenceDTO> skills,
        String url
) {
}
