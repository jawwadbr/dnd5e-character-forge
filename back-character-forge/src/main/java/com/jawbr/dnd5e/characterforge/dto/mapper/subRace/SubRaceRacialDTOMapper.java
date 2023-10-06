package com.jawbr.dnd5e.characterforge.dto.mapper.subRace;

import com.jawbr.dnd5e.characterforge.dto.response.subRace.SubRaceRacialDTO;
import com.jawbr.dnd5e.characterforge.model.entity.Race;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Method to map {@link Race} entity to {@link SubRaceRacialDTO}
 *
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Service
public class SubRaceRacialDTOMapper implements Function<Race, SubRaceRacialDTO> {

    @Override
    public SubRaceRacialDTO apply(Race race) {
        return new SubRaceRacialDTO(
                race.getIndexName(),
                race.getRaceName(),
                race.getUrl()
        );
    }
}
