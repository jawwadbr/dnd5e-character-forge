package com.jawbr.dnd5e.characterforge.dto.mapper.race;

import com.jawbr.dnd5e.characterforge.dto.response.race.RaceLanguagesDTO;
import com.jawbr.dnd5e.characterforge.model.entity.Language;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Method to map {@link Language} entity to {@link RaceLanguagesDTO}
 *
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Service
public class RaceLanguagesDTOMapper implements Function<Language, RaceLanguagesDTO> {

    @Override
    public RaceLanguagesDTO apply(Language language) {
        return new RaceLanguagesDTO(
                language.getIndexName(),
                language.getName(),
                language.getUrl()
        );
    }
}
