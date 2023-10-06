package com.jawbr.dnd5e.characterforge.dto.response.subRace;

import lombok.Builder;

@Builder
public record SubRaceRacialDTO(
        String index,
        String name,
        String url
) {
}
