package com.jawbr.dnd5e.characterforge.dto.mapper.race;

import com.jawbr.dnd5e.characterforge.dto.response.race.RaceProficiencyDTO;
import com.jawbr.dnd5e.characterforge.model.entity.Proficiency;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Method to map {@link Proficiency} entity to {@link RaceProficiencyDTO}
 *
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Service
public class RaceProficiencyDTOMapper implements Function<Proficiency, RaceProficiencyDTO> {

    @Override
    public RaceProficiencyDTO apply(Proficiency proficiency) {
        return new RaceProficiencyDTO(
                proficiency.getIndexName(),
                proficiency.getName(),
                proficiency.getUrl()
        );
    }
}
