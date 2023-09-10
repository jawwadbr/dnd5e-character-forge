package com.jawbr.dnd5e.characterforge.dto.response.language;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jawbr.dnd5e.characterforge.model.util.LanguageType;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
