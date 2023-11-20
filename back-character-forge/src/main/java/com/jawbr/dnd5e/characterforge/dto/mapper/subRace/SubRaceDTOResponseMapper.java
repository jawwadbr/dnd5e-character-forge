package com.jawbr.dnd5e.characterforge.dto.mapper.subRace;

import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.model.entity.SubRace;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
public class SubRaceDTOResponseMapper implements Function<List<SubRace>, FindAllDTOResponse> {

    @Override
    public FindAllDTOResponse apply(List<SubRace> subRaces) {

        List<EntityReferenceDTO> list = new ArrayList<>();
        for(SubRace subRace : subRaces) {
            list.add(EntityReferenceDTO.builder()
                    .name(subRace.getSubRaceName())
                    .index(subRace.getIndexName())
                    .url(subRace.getUrl())
                    .build());
        }

        return new FindAllDTOResponse(
                subRaces.size(),
                list
        );
    }
}
