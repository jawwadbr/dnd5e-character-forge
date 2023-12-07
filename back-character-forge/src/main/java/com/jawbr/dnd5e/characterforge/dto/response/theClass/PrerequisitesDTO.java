package com.jawbr.dnd5e.characterforge.dto.response.theClass;

import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import lombok.Builder;

@Builder
public record PrerequisitesDTO(
        EntityReferenceDTO ability_score,
        int minimum_score
) {
}
