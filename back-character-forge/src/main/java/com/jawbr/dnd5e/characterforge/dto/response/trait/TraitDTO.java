package com.jawbr.dnd5e.characterforge.dto.response.trait;

import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import lombok.Builder;

import java.util.List;

@Builder
public record TraitDTO(
        String index,
        List<EntityReferenceDTO> races,
        List<EntityReferenceDTO> subraces,
        String name,
        String desc,
        List<EntityReferenceDTO> proficiencies,
        /*
         * proficiency_choices
         * trait_specific -- subtrait_options
         * language_options
         */
        String url
) {
}
