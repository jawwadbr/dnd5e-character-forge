package com.jawbr.dnd5e.characterforge.dto.mapper.proficiency;

import com.jawbr.dnd5e.characterforge.dto.response.ResultListDTOResponse;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.model.entity.Proficiency;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
public class ProficiencyDTOResponseMapper implements Function<List<Proficiency>, FindAllDTOResponse> {

    @Override
    public FindAllDTOResponse apply(List<Proficiency> proficiencies) {

        List<ResultListDTOResponse> list = new ArrayList<>();
        for(Proficiency proficiency : proficiencies) {
            list.add(ResultListDTOResponse.builder()
                    .name(proficiency.getName())
                    .index(proficiency.getIndexName())
                    .url(proficiency.getUrl())
                    .build());
        }

        return new FindAllDTOResponse(
                proficiencies.size(),
                list
        );
    }
}
