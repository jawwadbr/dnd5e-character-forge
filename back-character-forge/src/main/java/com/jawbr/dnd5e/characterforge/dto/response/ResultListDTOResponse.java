package com.jawbr.dnd5e.characterforge.dto.response;

import lombok.Builder;

@Builder
public record ResultListDTOResponse(
        String index,
        String name,
        String url
) {
}
