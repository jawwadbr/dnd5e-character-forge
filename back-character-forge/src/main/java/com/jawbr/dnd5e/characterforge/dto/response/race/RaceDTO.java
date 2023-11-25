package com.jawbr.dnd5e.characterforge.dto.response.race;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.OptionSetDTO;
import com.jawbr.dnd5e.characterforge.model.util.Size;
import lombok.Builder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL) // Only show in the response fields that is not null
@Builder
public record RaceDTO(
        String index,
        String name,
        int speed,
        List<RaceAbilityBonusesDTO> ability_bonuses,
        OptionSetDTO ability_bonus_options,
        String alignment,
        String age,
        Size size,
        String size_description,
        List<EntityReferenceDTO> starting_proficiencies,
        OptionSetDTO starting_proficiency_options,
        List<EntityReferenceDTO> languages,
        OptionSetDTO language_options,
        String language_desc,
        List<EntityReferenceDTO> traits,
        List<EntityReferenceDTO> subraces,
        String url
) {
}
