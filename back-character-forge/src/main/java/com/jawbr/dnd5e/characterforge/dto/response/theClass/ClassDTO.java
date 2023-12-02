package com.jawbr.dnd5e.characterforge.dto.response.theClass;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.OptionSetDTO;
import lombok.Builder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record ClassDTO(
        String index,
        String name,
        int hit_die,
        OptionSetDTO proficiency_choices,
        List<EntityReferenceDTO> proficiencies,
        // saving throws
        // starting equips
        // starting equips options
        // class levels
        // multiclassing - might be done later or not even be implemented
        // subclasses
        // spellcasting
        // spells
        String url
) {
}
