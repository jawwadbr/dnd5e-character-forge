package com.jawbr.dnd5e.characterforge.dto.mapper.proficiency;

import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.proficiency.ProficiencyDTO;
import com.jawbr.dnd5e.characterforge.model.entity.Proficiency;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Method to map {@link Proficiency} entity to {@link ProficiencyDTO}
 *
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Service
public class ProficiencyDTOMapper implements Function<Proficiency, ProficiencyDTO> {

    @Override
    public ProficiencyDTO apply(Proficiency proficiency) {

        List<EntityReferenceDTO> proficiencyRaceDTOS = Stream.concat(
                proficiency.getRaces().stream().map(race -> EntityReferenceDTO.builder()
                        .name(race.getEntityName())
                        .index(race.getIndexName())
                        .url(race.getUrl())
                        .build()),
                proficiency.getSubRaces().stream().map(subRace -> EntityReferenceDTO.builder()
                        .name(subRace.getEntityName())
                        .index(subRace.getIndexName())
                        .url(subRace.getUrl())
                        .build())
        ).collect(Collectors.toList());

        return new ProficiencyDTO(
                proficiency.getIndexName(),
                proficiency.getType(),
                proficiency.getName(),
                proficiencyRaceDTOS,
                proficiency.getUrl()
        );
    }
}
