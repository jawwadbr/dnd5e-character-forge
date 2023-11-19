package com.jawbr.dnd5e.characterforge.dto.mapper.skill;

import com.jawbr.dnd5e.characterforge.dto.response.ResultListDTOResponse;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.model.entity.Skill;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
public class SkillDTOResponseMapper implements Function<List<Skill>, FindAllDTOResponse> {

    @Override
    public FindAllDTOResponse apply(List<Skill> skills) {

        List<ResultListDTOResponse> list = new ArrayList<>();
        for(Skill skill : skills) {
            list.add(ResultListDTOResponse.builder()
                    .name(skill.getName())
                    .index(skill.getIndexName())
                    .url(skill.getUrl())
                    .build());
        }

        return new FindAllDTOResponse(
                skills.size(),
                list
        );
    }
}
