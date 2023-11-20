package com.jawbr.dnd5e.characterforge.dto.mapper.language;

import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.model.entity.Language;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
public class LanguageDTOResponseMapper implements Function<List<Language>, FindAllDTOResponse> {

    @Override
    public FindAllDTOResponse apply(List<Language> languages) {

        List<EntityReferenceDTO> list = new ArrayList<>();
        for(Language language : languages) {
            list.add(EntityReferenceDTO.builder()
                    .index(language.getIndexName())
                    .name(language.getName())
                    .url(language.getUrl())
                    .build());
        }

        return new FindAllDTOResponse(
                languages.size(),
                list
        );
    }
}
