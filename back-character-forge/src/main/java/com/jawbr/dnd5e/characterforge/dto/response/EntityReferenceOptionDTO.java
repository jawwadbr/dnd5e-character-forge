package com.jawbr.dnd5e.characterforge.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record EntityReferenceOptionDTO(
        EntityReferenceDTO item,
        EntityReferenceDTO ability_score,
        Integer bonus
) {
}
