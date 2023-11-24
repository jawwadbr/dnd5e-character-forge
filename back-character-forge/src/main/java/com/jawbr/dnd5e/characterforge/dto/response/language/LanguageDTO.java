package com.jawbr.dnd5e.characterforge.dto.response.language;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jawbr.dnd5e.characterforge.model.util.LanguageType;
import lombok.Builder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record LanguageDTO(
        String index,
        String name,
        String desc,
        LanguageType type,
        List<String> typical_speakers,
        String script,
        String url
) {
}
