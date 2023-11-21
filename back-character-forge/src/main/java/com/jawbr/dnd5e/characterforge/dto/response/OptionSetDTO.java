package com.jawbr.dnd5e.characterforge.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jawbr.dnd5e.characterforge.model.util.OptionType;
import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record OptionSetDTO(
        int choose,
        String desc,
        OptionType type,
        FromOptionSetDTO from
) {
}
