package com.jawbr.dnd5e.characterforge.dto.response.subRace;

import lombok.Builder;

@Builder
public record SubRaceAbilityScoreDTO(
        String index,
        String name,
        String url
) {

}
