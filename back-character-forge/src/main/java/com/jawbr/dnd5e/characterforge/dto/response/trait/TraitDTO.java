package com.jawbr.dnd5e.characterforge.dto.response.trait;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.OptionSetDTO;
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
        OptionSetDTO language_options,
        /*
         * proficiency_choices
         * trait_specific -- subtrait_options
         */
        String url
) {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public OptionSetDTO getLanguage_options() {
        return language_options;
    }
}
