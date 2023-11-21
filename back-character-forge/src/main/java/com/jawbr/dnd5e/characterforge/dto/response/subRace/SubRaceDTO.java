package com.jawbr.dnd5e.characterforge.dto.response.subRace;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.OptionSetDTO;
import lombok.Builder;

import java.util.List;

@Builder
public record SubRaceDTO(
        String index,
        String name,
        EntityReferenceDTO race,
        String desc,
        List<SubRaceAbilityScoreBonusDTO> ability_bonuses,
        List<EntityReferenceDTO> starting_proficiencies,
        List<EntityReferenceDTO> languages,
        OptionSetDTO language_options,
        List<EntityReferenceDTO> racial_traits,
        String url
) {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public OptionSetDTO getLanguage_options() {
        return language_options;
    }
}
