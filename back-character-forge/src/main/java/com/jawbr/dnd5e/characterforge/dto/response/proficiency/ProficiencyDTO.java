package com.jawbr.dnd5e.characterforge.dto.response.proficiency;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.model.util.ProficiencyType;
import lombok.Builder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record ProficiencyDTO(
        String index,
        ProficiencyType type,
        String name,
        List<EntityReferenceDTO> classes,
        List<EntityReferenceDTO> races,
        String url
        // reference
) {
}
