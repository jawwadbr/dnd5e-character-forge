package com.jawbr.dnd5e.characterforge.dto.mapper.subRace;

import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.subRace.SubRaceDTO;
import com.jawbr.dnd5e.characterforge.model.entity.Proficiency;
import com.jawbr.dnd5e.characterforge.model.entity.SubRace;
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
                //languages
                //language_options
                //racial_traits
                subRace.getUrl()
        );
    }
}
