package com.jawbr.dnd5e.characterforge.dto.mapper.proficiency;

import com.jawbr.dnd5e.characterforge.dto.response.proficiency.ProficiencyRaceDTO;
import com.jawbr.dnd5e.characterforge.model.entity.Race;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Method to map {@link Race} entity to {@link ProficiencyRaceDTO}
 *
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Service
public class ProficiencyRaceDTOMapper implements Function<Race, ProficiencyRaceDTO> {

    @Override
    public ProficiencyRaceDTO apply(Race race) {
        return new ProficiencyRaceDTO(
                race.getIndexName(),
                race.getRaceName(),
                race.getUrl()
        );
    }
}
