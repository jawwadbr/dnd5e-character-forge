package com.jawbr.dnd5e.characterforge.dto.mapper.race;

import com.jawbr.dnd5e.characterforge.dto.response.race.RaceDTOList;
import com.jawbr.dnd5e.characterforge.dto.response.race.RaceDTOResponse;
import com.jawbr.dnd5e.characterforge.model.entity.Race;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Method to map a list of {@link Race} entities to {@link RaceDTOResponse}
 *
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Service
public class RaceDTOResponseMapper implements Function<List<Race>, RaceDTOResponse> {

    @Override
    public RaceDTOResponse apply(List<Race> races) {

        List<RaceDTOList> raceDTOLists = new ArrayList<>();
        for(Race race : races) {
            raceDTOLists.add(RaceDTOList.builder()
                    .name(race.getRaceName())
                    .index(race.getIndexName())
                    .url(race.getUrl())
                    .build());
        }

        return new RaceDTOResponse(
                races.size(),
                raceDTOLists
        );
    }
}
