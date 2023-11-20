package com.jawbr.dnd5e.characterforge.dto.mapper.race;

import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.race.RaceDTO;
import com.jawbr.dnd5e.characterforge.model.entity.Language;
import com.jawbr.dnd5e.characterforge.model.entity.Proficiency;
import com.jawbr.dnd5e.characterforge.model.entity.Race;
import com.jawbr.dnd5e.characterforge.model.entity.SubRace;
import com.jawbr.dnd5e.characterforge.model.entity.Trait;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Method to map {@link Race} entity to {@link RaceDTO}
 *
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Service
public class RaceDTOMapper implements Function<Race, RaceDTO> {

    private final RaceAbilityBonusesDTOMapper raceAbilityBonusesDTOMapper;

    public RaceDTOMapper(RaceAbilityBonusesDTOMapper raceAbilityBonusesDTOMapper) {
        this.raceAbilityBonusesDTOMapper = raceAbilityBonusesDTOMapper;
    }

    @Override
    public RaceDTO apply(Race race) {

        List<EntityReferenceDTO> proficienciesList = new ArrayList<>();
        for(Proficiency proficiency : race.getProficiencies()) {
            proficienciesList.add(EntityReferenceDTO.builder()
                    .name(proficiency.getName())
                    .index(proficiency.getIndexName())
                    .url(proficiency.getUrl())
                    .build());
        }

        List<EntityReferenceDTO> languagesList = new ArrayList<>();
        for(Language language : race.getLanguages()) {
            languagesList.add(EntityReferenceDTO.builder()
                    .name(language.getName())
                    .index(language.getIndexName())
                    .url(language.getUrl())
                    .build());
        }

        List<EntityReferenceDTO> subRacesList = new ArrayList<>();
        for(SubRace subRace : race.getSubRaces()) {
            subRacesList.add(EntityReferenceDTO.builder()
                    .name(subRace.getSubRaceName())
                    .index(subRace.getIndexName())
                    .url(subRace.getUrl())
                    .build());
        }

        List<EntityReferenceDTO> traitsList = new ArrayList<>();
        for(Trait trait : race.getTraits()) {
            traitsList.add(EntityReferenceDTO.builder()
                    .name(trait.getTraitName())
                    .index(trait.getIndexName())
                    .url(trait.getUrl())
                    .build());
        }

        return new RaceDTO(
                race.getIndexName(),
                race.getRaceName(),
                race.getSpeed(),
                race.getAbilityBonuses()
                        .stream()
                        .map(raceAbilityBonusesDTOMapper)
                        .collect(Collectors.toList()),
                race.getAlignment(),
                race.getAge_desc(),
                race.getSize(),
                race.getSize_desc(),
                proficienciesList,
                languagesList,
                race.getLanguage_desc(),
                traitsList,
                subRacesList,
                race.getUrl()
        );
    }
}
