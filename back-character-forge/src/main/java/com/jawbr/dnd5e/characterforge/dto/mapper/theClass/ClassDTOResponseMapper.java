package com.jawbr.dnd5e.characterforge.dto.mapper.theClass;

import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.model.entity.Class;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
public class ClassDTOResponseMapper implements Function<List<Class>, FindAllDTOResponse> {

    @Override
    public FindAllDTOResponse apply(List<Class> classes) {

        List<EntityReferenceDTO> classesList = new ArrayList<>();
        for(Class theClass : classes) {
            classesList.add(EntityReferenceDTO.builder()
                    .name(theClass.getClassName())
                    .index(theClass.getIndexName())
                    .url(theClass.getUrl())
                    .build());
        }

        return new FindAllDTOResponse(
                classesList.size(),
                classesList
        );
    }
}
