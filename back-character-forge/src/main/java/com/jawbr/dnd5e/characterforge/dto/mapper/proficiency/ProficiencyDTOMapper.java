package com.jawbr.dnd5e.characterforge.dto.mapper.proficiency;

import com.jawbr.dnd5e.characterforge.dto.response.proficiency.ProficiencyDTO;
import com.jawbr.dnd5e.characterforge.model.entity.Proficiency;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Method to map {@link Proficiency} entity to {@link ProficiencyDTO}
 *
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Service
public class ProficiencyDTOMapper implements Function<Proficiency, ProficiencyDTO> {

    private final ProficiencyRaceDTOMapper proficiencyRaceDTOMapper;

    public ProficiencyDTOMapper(ProficiencyRaceDTOMapper proficiencyRaceDTOMapper) {
        this.proficiencyRaceDTOMapper = proficiencyRaceDTOMapper;
    }

    @Override
    public ProficiencyDTO apply(Proficiency proficiency) {
        return new ProficiencyDTO(
                proficiency.getIndexName(),
                proficiency.getType(),
                proficiency.getName(),
                proficiency.getRaces()
                        .stream()
                        .map(proficiencyRaceDTOMapper)
                        .collect(Collectors.toList()),
                proficiency.getUrl()
        );
    }
}
