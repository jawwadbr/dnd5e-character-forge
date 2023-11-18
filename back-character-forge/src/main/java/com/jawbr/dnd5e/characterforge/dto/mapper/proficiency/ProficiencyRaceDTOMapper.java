package com.jawbr.dnd5e.characterforge.dto.mapper.proficiency;

import com.jawbr.dnd5e.characterforge.dto.response.proficiency.ProficiencyRaceDTO;
import com.jawbr.dnd5e.characterforge.model.entity.Race;
import com.jawbr.dnd5e.characterforge.model.entity.SubRace;
import com.jawbr.dnd5e.characterforge.model.util.RaceEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Method to map using a polymorphism interface {@link RaceEntity} for {@link Race} and {@link SubRace} to {@link ProficiencyRaceDTO}
 *
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Service
public class ProficiencyRaceDTOMapper implements Function<RaceEntity, ProficiencyRaceDTO> {

    @Override
    public ProficiencyRaceDTO apply(RaceEntity raceEntity) {
        return new ProficiencyRaceDTO(
                raceEntity.getIndexName(),
                raceEntity.getEntityName(),
                raceEntity.getUrl());
    }
}
