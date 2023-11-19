package com.jawbr.dnd5e.characterforge.service;

import com.github.slugify.Slugify;
import com.jawbr.dnd5e.characterforge.dto.mapper.language.LanguageDTOMapper;
import com.jawbr.dnd5e.characterforge.dto.mapper.language.LanguageDTOResponseMapper;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.dto.response.language.LanguageDTO;
import com.jawbr.dnd5e.characterforge.exception.LanguageNotFoundException;
import com.jawbr.dnd5e.characterforge.repository.LanguageRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Service
public class LanguageService {

    private final LanguageRepository languageRepository;
    private final LanguageDTOMapper languageDTOMapper;
    private final LanguageDTOResponseMapper languageDTOResponseMapper;
    private final Slugify slugify;

    private static final String URL = "/api/languages/";

    public LanguageService(LanguageRepository languageRepository,
                           LanguageDTOMapper languageDTOMapper, LanguageDTOResponseMapper languageDTOResponseMapper)
    {
        this.languageRepository = languageRepository;
        this.languageDTOMapper = languageDTOMapper;
        this.languageDTOResponseMapper = languageDTOResponseMapper;
        this.slugify = Slugify.builder().build();
    }

    /**
     * Method to find all languages inside the database
     *
     * @return a List containing all languages mapped into LanguageDTO
     * @throws LanguageNotFoundException when no languages are found inside the database
     * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
     */
    public FindAllDTOResponse findAllLanguages() {
        return Optional.of(languageRepository.findAll())
                .filter(list -> !list.isEmpty())
                .map(languageDTOResponseMapper)
                .orElseThrow(() -> new LanguageNotFoundException("No languages found."));
    }

    /**
     * Method to find language inside the database using Index Name
     *
     * @return a language mapped into LanguageDTO
     * @throws LanguageNotFoundException when no languages are found inside the database
     * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
     */
    public LanguageDTO findLanguageByIndexName(String indexName) {
        return Optional.of(languageRepository.findByIndexName(indexName))
                .map(languageDTOMapper)
                .orElseThrow(() -> new LanguageNotFoundException(String.format("Language with index name '%s' not found.", indexName)));
    }
}
