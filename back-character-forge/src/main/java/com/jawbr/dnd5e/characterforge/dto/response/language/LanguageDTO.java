package com.jawbr.dnd5e.characterforge.dto.response.language;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jawbr.dnd5e.characterforge.model.util.LanguageType;
import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record LanguageDTO(
        String index,
        String name,
        String desc,
        LanguageType type,
        // typical_speakers
        String script,
        String url
) {
}
