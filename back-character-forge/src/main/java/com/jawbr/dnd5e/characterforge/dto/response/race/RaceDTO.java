package com.jawbr.dnd5e.characterforge.dto.response.race;

import com.jawbr.dnd5e.characterforge.model.util.Size;
import lombok.Builder;

import java.util.List;

@Builder
public record RaceDTO(
        String index,
        String name,
        int speed,
        List<RaceAbilityBonusesDTO> ability_bonuses,
        String alignment,
        String age,
        Size size,
        String size_description,
        // starting_proficiencies []  --- Can have options
        // languages []
        String language_desc,
        // traits []
        String url
) {
}
