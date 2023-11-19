package com.jawbr.dnd5e.characterforge.dto.mapper.race;

import com.jawbr.dnd5e.characterforge.dto.response.DTOResultListResponse;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.model.entity.Race;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Method to map a list of {@link Race} entities to {@link FindAllDTOResponse}
 *
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Service
public class RaceDTOResponseMapper implements Function<List<Race>, FindAllDTOResponse> {

    @Override
    public FindAllDTOResponse apply(List<Race> races) {

        List<DTOResultListResponse> raceDTOLists = new ArrayList<>();
        for(Race race : races) {
            raceDTOLists.add(DTOResultListResponse.builder()
                    .name(race.getRaceName())
                    .index(race.getIndexName())
                    .url(race.getUrl())
                    .build());
        }

        return new FindAllDTOResponse(
                races.size(),
                raceDTOLists
        );
    }
}
