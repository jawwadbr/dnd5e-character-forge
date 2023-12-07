package com.jawbr.dnd5e.characterforge.dto.response.theClass;

import lombok.Builder;

import java.util.List;

@Builder
public record MultiClassingPrerequisitesDTO(
        List<PrerequisitesDTO> prerequisites
) {
}
