package com.jawbr.dnd5e.characterforge.dto.response.race;

import java.util.List;

public record RaceDTOResponse(
        int count,
        List<RaceDTOList> results
) {
}
