package com.jawbr.dnd5e.characterforge.dto.mapper.subRace;

import com.jawbr.dnd5e.characterforge.dto.response.subRace.SubRaceDTO;
import com.jawbr.dnd5e.characterforge.model.entity.SubRace;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Method to map {@link SubRace} entity to {@link SubRaceDTO}
 *
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Service
public class SubRaceDTOMapper implements Function<SubRace, SubRaceDTO> {

    private final SubRaceRacialDTOMapper subRaceRacialDTOMapper;

    public SubRaceDTOMapper(SubRaceRacialDTOMapper subRaceRacialDTOMapper) {
        this.subRaceRacialDTOMapper = subRaceRacialDTOMapper;
    }

    @Override
    public SubRaceDTO apply(SubRace subRace) {
        return new SubRaceDTO(
                subRace.getIndexName(),
                subRace.getSubRaceName(),
                subRaceRacialDTOMapper.apply(subRace.getRace()),
                subRace.getDesc(),
                //ability bonuses
                //starting_proficiencies
                //languages
                //language_options
                //racial_traits
                subRace.getUrl()
        );
    }
}
