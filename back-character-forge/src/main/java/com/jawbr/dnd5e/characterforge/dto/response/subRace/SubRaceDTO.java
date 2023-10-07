package com.jawbr.dnd5e.characterforge.dto.response.subRace;

import lombok.Builder;

import java.util.List;

@Builder
public record SubRaceDTO(
        String index,
        String name,
        SubRaceRacialDTO race,
        String desc,
        List<SubRaceAbilityScoreBonusDTO> ability_bonuses,
        //starting_proficiencies
        //languages
        //language_options
        //racial_traits
        String url
) {
}
