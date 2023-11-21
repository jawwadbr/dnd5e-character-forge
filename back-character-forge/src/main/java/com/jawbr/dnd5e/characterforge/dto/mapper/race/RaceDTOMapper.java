package com.jawbr.dnd5e.characterforge.dto.mapper.race;

import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceOptionDTO;
import com.jawbr.dnd5e.characterforge.dto.response.FromOptionSetDTO;
import com.jawbr.dnd5e.characterforge.dto.response.OptionSetDTO;
import com.jawbr.dnd5e.characterforge.dto.response.race.RaceDTO;
import com.jawbr.dnd5e.characterforge.model.entity.AbilityScore;
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

        List<EntityReferenceOptionDTO> languageFromOptions = new ArrayList<>();
        OptionSetDTO languageOptions = null;
        if(race.getLanguageOptions() != null) {
            for(Language language : race.getLanguageOptions().getFrom()) {
                languageFromOptions.add(EntityReferenceOptionDTO.builder()
                        .item(EntityReferenceDTO.builder()
                                .index(language.getIndexName())
                                .name(language.getName())
                                .url(language.getUrl())
                                .build())
                        .build());
            }
            languageOptions = OptionSetDTO.builder()
                    .choose(race.getLanguageOptions().getChoose())
                    .desc(race.getLanguageOptions().getDesc())
                    .type(race.getLanguageOptions().getType())
                    .from(FromOptionSetDTO.builder()
                            .options(languageFromOptions)
                            .build())
                    .build();
        }

        List<EntityReferenceOptionDTO> abilityScoreBonusesFromOptions = new ArrayList<>();
        OptionSetDTO abilityScoreBonusesOptions = null;
        if(race.getAbilityBonusesOptions() != null) {
            for(AbilityScore abilityScore : race.getAbilityBonusesOptions().getFrom()) {
                abilityScoreBonusesFromOptions.add(EntityReferenceOptionDTO.builder()
                        .ability_score(EntityReferenceDTO.builder()
                                .index(abilityScore.getIndexName())
                                .name(abilityScore.getShortName())
                                .url(abilityScore.getUrl())
                                .build())
                        .bonus(race.getAbilityBonusesOptions().getBonus())
                        .build());
            }
            abilityScoreBonusesOptions = OptionSetDTO.builder()
                    .choose(race.getAbilityBonusesOptions().getChoose())
                    .desc(race.getAbilityBonusesOptions().getDesc())
                    .type(race.getAbilityBonusesOptions().getType())
                    .from(FromOptionSetDTO.builder()
                            .options(abilityScoreBonusesFromOptions)
                            .build())
                    .build();
        }

        List<EntityReferenceOptionDTO> startingProficienciesFromOptions = new ArrayList<>();
        OptionSetDTO startingProficiencies = null;
        if(race.getProficiencyOptions() != null) {
            for(Proficiency proficiency : race.getProficiencyOptions().getFrom()) {
                startingProficienciesFromOptions.add(EntityReferenceOptionDTO.builder()
                        .item(EntityReferenceDTO.builder()
                                .index(proficiency.getIndexName())
                                .name(proficiency.getName())
                                .url(proficiency.getUrl())
                                .build())
                        .build());
            }
            startingProficiencies = OptionSetDTO.builder()
                    .choose(race.getProficiencyOptions().getChoose())
                    .desc(race.getProficiencyOptions().getDesc())
                    .type(race.getProficiencyOptions().getType())
                    .from(FromOptionSetDTO.builder()
                            .options(startingProficienciesFromOptions)
                            .build())
                    .build();
        }

        return new RaceDTO(
                race.getIndexName(),
                race.getRaceName(),
                race.getSpeed(),
                race.getAbilityBonuses()
                        .stream()
                        .map(raceAbilityBonusesDTOMapper)
                        .collect(Collectors.toList()),
                abilityScoreBonusesOptions,
                race.getAlignment(),
                race.getAge_desc(),
                race.getSize(),
                race.getSize_desc(),
                proficienciesList,
                startingProficiencies,
                languagesList,
                languageOptions,
                race.getLanguage_desc(),
                traitsList,
                subRacesList,
                race.getUrl()
        );
    }
}
