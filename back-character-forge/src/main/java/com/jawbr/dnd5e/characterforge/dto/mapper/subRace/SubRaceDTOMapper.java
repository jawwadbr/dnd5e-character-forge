package com.jawbr.dnd5e.characterforge.dto.mapper.subRace;

import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceOptionDTO;
import com.jawbr.dnd5e.characterforge.dto.response.FromOptionSetDTO;
import com.jawbr.dnd5e.characterforge.dto.response.OptionSetDTO;
import com.jawbr.dnd5e.characterforge.dto.response.subRace.SubRaceDTO;
import com.jawbr.dnd5e.characterforge.model.entity.Language;
import com.jawbr.dnd5e.characterforge.model.entity.Proficiency;
import com.jawbr.dnd5e.characterforge.model.entity.SubRace;
import com.jawbr.dnd5e.characterforge.model.entity.Trait;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Method to map {@link SubRace} entity to {@link SubRaceDTO}
 *
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Service
public class SubRaceDTOMapper implements Function<SubRace, SubRaceDTO> {

    private final SubRaceAbilityScoreBonusDTOMapper subRaceAbilityScoreBonusDTOMapper;

    public SubRaceDTOMapper(SubRaceAbilityScoreBonusDTOMapper subRaceAbilityScoreBonusDTOMapper) {
        this.subRaceAbilityScoreBonusDTOMapper = subRaceAbilityScoreBonusDTOMapper;
    }


    @Override
    public SubRaceDTO apply(SubRace subRace) {

        List<EntityReferenceDTO> proficienciesList = new ArrayList<>();
        for(Proficiency proficiency : subRace.getProficiencies()) {
            proficienciesList.add(EntityReferenceDTO.builder()
                    .name(proficiency.getName())
                    .index(proficiency.getIndexName())
                    .url(proficiency.getUrl())
                    .build());
        }

        List<EntityReferenceDTO> languagesList = new ArrayList<>();
        for(Language language : subRace.getLanguages()) {
            languagesList.add(EntityReferenceDTO.builder()
                    .name(language.getName())
                    .index(language.getIndexName())
                    .url(language.getUrl())
                    .build());
        }

        List<EntityReferenceDTO> traitsList = new ArrayList<>();
        for(Trait trait : subRace.getTraits()) {
            traitsList.add(EntityReferenceDTO.builder()
                    .name(trait.getTraitName())
                    .index(trait.getIndexName())
                    .url(trait.getUrl())
                    .build());
        }

        List<EntityReferenceOptionDTO> languageFromOptions = new ArrayList<>();
        OptionSetDTO languageOptions = null;
        if(subRace.getLanguageOptions() != null) {
            for(Language language : subRace.getLanguageOptions().getFrom()) {
                languageFromOptions.add(EntityReferenceOptionDTO.builder()
                        .item(EntityReferenceDTO.builder()
                                .index(language.getIndexName())
                                .name(language.getName())
                                .url(language.getUrl())
                                .build())
                        .build());
            }
            languageOptions = OptionSetDTO.builder()
                    .choose(subRace.getLanguageOptions().getChoose())
                    .desc(subRace.getLanguageOptions().getDesc())
                    .type(subRace.getLanguageOptions().getType())
                    .from(FromOptionSetDTO.builder()
                            .options(languageFromOptions)
                            .build())
                    .build();
        }

        return new SubRaceDTO(
                subRace.getIndexName(),
                subRace.getSubRaceName(),
                EntityReferenceDTO.builder()
                        .index(subRace.getRace().getIndexName())
                        .name(subRace.getRace().getRaceName())
                        .url(subRace.getRace().getUrl())
                        .build(),
                subRace.getDesc(),
                subRace.getAbilityBonuses()
                        .stream()
                        .map(subRaceAbilityScoreBonusDTOMapper)
                        .collect(Collectors.toList()),
                proficienciesList,
                languagesList,
                languageOptions,
                traitsList,
                subRace.getUrl()
        );
    }
}
