package com.jawbr.dnd5e.characterforge.dto.mapper.theClass;

import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceOptionDTO;
import com.jawbr.dnd5e.characterforge.dto.response.FromOptionSetDTO;
import com.jawbr.dnd5e.characterforge.dto.response.OptionSetDTO;
import com.jawbr.dnd5e.characterforge.dto.response.theClass.ClassDTO;
import com.jawbr.dnd5e.characterforge.model.entity.Class;
import com.jawbr.dnd5e.characterforge.model.entity.Proficiency;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
public class ClassDTOMapper implements Function<Class, ClassDTO> {

    @Override
    public ClassDTO apply(Class aClass) {

        List<EntityReferenceOptionDTO> proficiencyFromOptions = new ArrayList<>();
        OptionSetDTO proficiencyOptions = null;
        if(aClass.getProficiencyChoices() != null) {
            for(Proficiency proficiency : aClass.getProficiencyChoices().getFrom()) {
                proficiencyFromOptions.add(EntityReferenceOptionDTO.builder()
                        .item(EntityReferenceDTO.builder()
                                .index(proficiency.getIndexName())
                                .name(proficiency.getName())
                                .url(proficiency.getUrl())
                                .build())
                        .build());
            }
            proficiencyOptions = OptionSetDTO.builder()
                    .choose(aClass.getProficiencyChoices().getChoose())
                    .desc(aClass.getProficiencyChoices().getDesc())
                    .type(aClass.getProficiencyChoices().getType())
                    .from(FromOptionSetDTO.builder()
                            .options(proficiencyFromOptions)
                            .build())
                    .build();
        }

        List<EntityReferenceDTO> proficinciesList = new ArrayList<>();
        for(Proficiency proficiency : aClass.getProficiencies()) {
            proficinciesList.add(EntityReferenceDTO.builder()
                    .url(proficiency.getUrl())
                    .index(proficiency.getIndexName())
                    .name(proficiency.getName())
                    .build());
        }

        return new ClassDTO(
                aClass.getIndexName(),
                aClass.getClassName(),
                aClass.getHitDie().getValue(),
                proficiencyOptions,
                proficinciesList,
                aClass.getUrl()
        );
    }
}
