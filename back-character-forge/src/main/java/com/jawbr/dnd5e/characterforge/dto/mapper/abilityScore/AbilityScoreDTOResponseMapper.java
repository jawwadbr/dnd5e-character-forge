package com.jawbr.dnd5e.characterforge.dto.mapper.abilityScore;

import com.jawbr.dnd5e.characterforge.dto.response.ResultListDTOResponse;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.model.entity.AbilityScore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Method to map a list of {@link AbilityScore} entities to {@link FindAllDTOResponse}
 *
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Service
public class AbilityScoreDTOResponseMapper implements Function<List<AbilityScore>, FindAllDTOResponse> {

    @Override
    public FindAllDTOResponse apply(List<AbilityScore> abilityScores) {

        List<ResultListDTOResponse> abilityScoreDTOList = new ArrayList<>();
        for(AbilityScore ab : abilityScores) {
            abilityScoreDTOList.add(ResultListDTOResponse.builder()
                    .name(ab.getShortName())
                    .index(ab.getIndexName())
                    .url(ab.getUrl())
                    .build());
        }

        return new FindAllDTOResponse(
                abilityScores.size(),
                abilityScoreDTOList
        );
    }
}
