package com.jawbr.dnd5e.characterforge.dto.mapper.trait;

import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.model.entity.Trait;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
public class TraitDTOResponseMapper implements Function<List<Trait>, FindAllDTOResponse> {

    @Override
    public FindAllDTOResponse apply(List<Trait> traits) {

        List<EntityReferenceDTO> list = new ArrayList<>();
        for(Trait trait : traits) {
            list.add(EntityReferenceDTO.builder()
                    .name(trait.getTraitName())
                    .index(trait.getIndexName())
                    .url(trait.getUrl())
                    .build());
        }

        return FindAllDTOResponse.builder()
                .count(traits.size())
                .results(list)
                .build();
    }
}
