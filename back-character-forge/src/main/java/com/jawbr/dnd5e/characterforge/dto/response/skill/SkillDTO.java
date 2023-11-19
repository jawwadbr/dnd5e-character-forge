package com.jawbr.dnd5e.characterforge.dto.response.skill;

import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import lombok.Builder;

import java.util.List;

@Builder
public record SkillDTO(
        String index,
        String name,
        String desc,
        EntityReferenceDTO ability_score,
        String url
) {
}
