package com.jawbr.dnd5e.characterforge.dto.mapper.theClass;

import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceOptionDTO;
import com.jawbr.dnd5e.characterforge.dto.response.FromOptionSetDTO;
import com.jawbr.dnd5e.characterforge.dto.response.OptionSetDTO;
import com.jawbr.dnd5e.characterforge.dto.response.theClass.ClassDTO;
import com.jawbr.dnd5e.characterforge.dto.response.theClass.MultiClassingPrerequisitesDTO;
import com.jawbr.dnd5e.characterforge.dto.response.theClass.PrerequisitesDTO;
import com.jawbr.dnd5e.characterforge.model.entity.AbilityScore;
import com.jawbr.dnd5e.characterforge.model.entity.Class;
import com.jawbr.dnd5e.characterforge.model.entity.MultiClassing;
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

        List<EntityReferenceDTO> savingThrowsList = new ArrayList<>();
        for(AbilityScore abilityScore : aClass.getSavingThrows()) {
            proficinciesList.add(EntityReferenceDTO.builder()
                    .url(abilityScore.getUrl())
                    .index(abilityScore.getIndexName())
                    .name(abilityScore.getShortName())
                    .build());
        }

        List<PrerequisitesDTO> prerequisitesDTOS = new ArrayList<>();
        if(aClass.getMultiClassingScores() != null) {
            for(MultiClassing multiClassing : aClass.getMultiClassingScores()) {
                prerequisitesDTOS.add(PrerequisitesDTO.builder()
                        .ability_score(EntityReferenceDTO.builder()
                                .index(multiClassing.getAbilityScore().getIndexName())
                                .url(multiClassing.getAbilityScore().getUrl())
                                .name(multiClassing.getAbilityScore().getShortName())
                                .build())
                        .minimum_score(multiClassing.getMinimumScore())
                        .build());
            }
        }
        MultiClassingPrerequisitesDTO prerequisites = MultiClassingPrerequisitesDTO.builder()
                .prerequisites(prerequisitesDTOS)
                .build();

        return new ClassDTO(
                aClass.getIndexName(),
                aClass.getClassName(),
                aClass.getHitDie().getValue(),
                proficiencyOptions,
                proficinciesList,
                savingThrowsList,
                prerequisites,
                aClass.getUrl()
        );
    }
}
