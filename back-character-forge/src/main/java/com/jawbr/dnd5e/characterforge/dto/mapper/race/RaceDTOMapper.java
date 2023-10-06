package com.jawbr.dnd5e.characterforge.dto.mapper.race;

import com.jawbr.dnd5e.characterforge.dto.response.race.RaceDTO;
import com.jawbr.dnd5e.characterforge.model.entity.Race;
import org.springframework.stereotype.Service;

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
    private final RaceLanguagesDTOMapper raceLanguagesDTOMapper;
    private final RaceProficiencyDTOMapper raceProficiencyDTOMapper;
    private final RaceSubRaceDTOMapper subRaceDTOMapper;

    public RaceDTOMapper(RaceAbilityBonusesDTOMapper raceAbilityBonusesDTOMapper,
                         RaceLanguagesDTOMapper raceLanguagesDTOMapper, RaceProficiencyDTOMapper raceProficiencyDTOMapper, RaceSubRaceDTOMapper subRaceDTOMapper)
    {
        this.raceAbilityBonusesDTOMapper = raceAbilityBonusesDTOMapper;
        this.raceLanguagesDTOMapper = raceLanguagesDTOMapper;
        this.raceProficiencyDTOMapper = raceProficiencyDTOMapper;
        this.subRaceDTOMapper = subRaceDTOMapper;
    }

    @Override
    public RaceDTO apply(Race race) {
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
                race.getProficiencies()
                        .stream()
                        .map(raceProficiencyDTOMapper)
                        .collect(Collectors.toList()),
                race.getLanguages()
                        .stream()
                        .map(raceLanguagesDTOMapper)
                        .collect(Collectors.toList()),
                race.getLanguage_desc(),
                race.getSubRaces()
                        .stream()
                        .map(subRaceDTOMapper)
                        .collect(Collectors.toList()),
                race.getUrl()
        );
    }
}
