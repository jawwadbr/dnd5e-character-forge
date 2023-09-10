package com.jawbr.dnd5e.characterforge.dto.mapper.language;

import com.jawbr.dnd5e.characterforge.dto.response.language.LanguageDTO;
import com.jawbr.dnd5e.characterforge.model.entity.Language;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Method to map {@link Language} entity to {@link LanguageDTO}
 *
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Service
public class LanguageDTOMapper implements Function<Language, LanguageDTO> {

    @Override
    public LanguageDTO apply(Language language) {
        return new LanguageDTO(
                language.getIndexName(),
                language.getName(),
                language.getLanguage_desc(),
                language.getType(),
                // typical speakers
                language.getScript(),
                language.getUrl()
        );
    }
}
