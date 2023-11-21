package com.jawbr.dnd5e.characterforge.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record FromOptionSetDTO(
        List<EntityReferenceDTO> options
) {
}
