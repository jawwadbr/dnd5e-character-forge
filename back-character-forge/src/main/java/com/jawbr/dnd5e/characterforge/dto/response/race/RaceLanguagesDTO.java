package com.jawbr.dnd5e.characterforge.dto.response.race;

import lombok.Builder;

@Builder
public record RaceLanguagesDTO(
        String index,
        String name,
        String url
) {
}
