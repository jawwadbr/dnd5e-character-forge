package com.jawbr.dnd5e.characterforge.dto.mapper.subRace;

import com.jawbr.dnd5e.characterforge.dto.mapper.race.RaceProficiencyDTOMapper;
import com.jawbr.dnd5e.characterforge.dto.response.subRace.SubRaceDTO;
import com.jawbr.dnd5e.characterforge.model.entity.SubRace;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Method to map {@link SubRace} entity to {@link SubRaceDTO}
 *
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Service
public class SubRaceDTOMapper implements Function<SubRace, SubRaceDTO> {

    private final SubRaceRacialDTOMapper subRaceRacialDTOMapper;
    private final SubRaceAbilityScoreBonusDTOMapper subRaceAbilityScoreBonusDTOMapper;
    private final RaceProficiencyDTOMapper raceProficiencyDTOMapper;

    public SubRaceDTOMapper(SubRaceRacialDTOMapper subRaceRacialDTOMapper, SubRaceAbilityScoreBonusDTOMapper subRaceAbilityScoreBonusDTOMapper, RaceProficiencyDTOMapper raceProficiencyDTOMapper) {
        this.subRaceRacialDTOMapper = subRaceRacialDTOMapper;
        this.subRaceAbilityScoreBonusDTOMapper = subRaceAbilityScoreBonusDTOMapper;
        this.raceProficiencyDTOMapper = raceProficiencyDTOMapper;
    }

    @Override
    public SubRaceDTO apply(SubRace subRace) {
        return new SubRaceDTO(
                subRace.getIndexName(),
                subRace.getSubRaceName(),
                subRaceRacialDTOMapper.apply(subRace.getRace()),
                subRace.getDesc(),
                subRace.getAbilityBonuses()
                        .stream()
                        .map(subRaceAbilityScoreBonusDTOMapper)
                        .collect(Collectors.toList()),
                subRace.getProficiencies()
                        .stream()
                        .map(raceProficiencyDTOMapper)
                        .collect(Collectors.toList()),
                //languages
                //language_options
                //racial_traits
                subRace.getUrl()
        );
    }
}
