package com.jawbr.dnd5e.characterforge.dto.response.subRace;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record SubRaceDTO(
        String index,
        String name,
        SubRaceRacialDTO race,
        String desc,
        //ability bonuses
        //starting_proficiencies
        //languages
        //language_options
        //racial_traits
        String url
) {
}
