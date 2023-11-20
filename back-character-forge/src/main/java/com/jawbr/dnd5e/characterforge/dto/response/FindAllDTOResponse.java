package com.jawbr.dnd5e.characterforge.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record FindAllDTOResponse(
        int count,
        List<EntityReferenceDTO> results
) {
}
