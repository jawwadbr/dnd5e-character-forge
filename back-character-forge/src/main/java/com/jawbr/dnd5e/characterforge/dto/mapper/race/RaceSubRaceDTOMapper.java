package com.jawbr.dnd5e.characterforge.dto.mapper.race;

import com.jawbr.dnd5e.characterforge.dto.response.race.RaceSubRaceDTO;
import com.jawbr.dnd5e.characterforge.model.entity.SubRace;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RaceSubRaceDTOMapper implements Function<SubRace, RaceSubRaceDTO> {

    @Override
    public RaceSubRaceDTO apply(SubRace subRace) {
        return new RaceSubRaceDTO(
                subRace.getIndexName(),
                subRace.getSubRaceName(),
                subRace.getUrl()
        );
    }
}
