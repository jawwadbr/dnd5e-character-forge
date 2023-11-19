package com.jawbr.dnd5e.characterforge.dto.response.subRace;

import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
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
        //languages
        //language_options
        //racial_traits
        String url
) {
}
